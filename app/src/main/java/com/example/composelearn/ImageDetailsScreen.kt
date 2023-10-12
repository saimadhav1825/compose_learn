package com.example.composelearn

import android.Manifest
import android.content.ContentResolver
import android.content.ContentUris
import android.location.Location
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ImageDetailsComposable() {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    var imageList by remember { mutableStateOf<List<ImageDetails>>(emptyList()) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        // Fetch image details on initial composition
        imageList = fetchImageDetails(context.contentResolver)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(imageList) { image ->
            ImageItem(image)
        }
    }
}

data class ImageDetails(
    val id: Long,
    val displayName: String,
    val contentUri: Uri,
    val location: Location?
)

@Composable
fun ImageItem(image: ImageDetails) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Handle image item click, e.g., open the image or show details
                Log.d("ImageDetails", "Clicked on image: ${image.displayName}")
                image.location?.let { location ->
                    Log.d("ImageDetails", "Location: Latitude=${location.latitude}, Longitude=${location.longitude}")
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .background(Color.Gray)
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(text = image.displayName, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Text(text = image.contentUri.toString())
            Text(text = image.location.toString())
        }
    }
}

private suspend fun fetchImageDetails(contentResolver: ContentResolver): List<ImageDetails> {
    return withContext(Dispatchers.IO) {
        val imageList = mutableListOf<ImageDetails>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )

        cursor?.use { c ->
            val idColumn = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val displayNameColumn = c.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (c.moveToNext()) {
                val id = c.getLong(idColumn)
                val displayName = c.getString(displayNameColumn)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val location = retrieveLocationFromExif(contentResolver, contentUri)

                val image = ImageDetails(id, displayName, contentUri, location)
                imageList.add(image)
            }
        }

        return@withContext imageList
    }
}

private fun retrieveLocationFromExif(contentResolver: ContentResolver, contentUri: Uri): Location? {
    return try {
        contentResolver.openInputStream(contentUri)?.use { inputStream ->
            val exifInterface = ExifInterface(inputStream)
            val latLong = FloatArray(2)
            if (exifInterface.getLatLong(latLong)) {
                val location = Location("")
                location.latitude = latLong[0].toDouble()
                location.longitude = latLong[1].toDouble()
                location
            } else {
                null // No GPS location data found
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
