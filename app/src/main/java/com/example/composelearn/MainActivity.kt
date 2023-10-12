package com.example.composelearn

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composelearn.ui.theme.ComposeLearnTheme
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//The URI of the photo that the user has picked
            var photoUri: Uri? by remember { mutableStateOf(null) }
            //The launcher we will use for the PickVisualMedia contract.
//When .launch()ed, this will display the photo picker.
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    //When the user has selected a photo, its URL is returned here
                    photoUri = uri
                }

            photoUri?.let {
                getRealPathFromURI(this, it)
                try {
                    this.contentResolver.openInputStream(it).use { inputStream ->
                        val exif = ExifInterface(inputStream!!)
                        Log.e("TAG", "inputStream Exception : ${exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)}")

                        val latitude = convertGPSLatitude(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)!!)

                        if (latitude != null) {
                            println("Latitude: $latitude degrees")
                        } else {
                            println("Failed to parse latitude.")
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            ComposeLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Button(
                            onClick = {
                                launcher.launch(
                                    PickVisualMediaRequest(
                                        //Here we request only photos. Change this to .ImageAndVideo if
                                        //you want videos too.
                                        //Or use .VideoOnly if you only want videos.
                                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(10.dp)
                        ) {
                            Text(text = "Pick Image From Gallery")
                        }
                        val painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(data = photoUri)
                                .build()
                        )
                        Image(
                            painter = painter,
                            contentScale = ContentScale.FillWidth,
                            contentDescription = "null",
                            modifier = Modifier
                                .padding(16.dp, 8.dp)
                                .size(100.dp)
                                .background(color = Color.White)
                                .clickable {

                                }
                        )
                    }
                }
            }
        }
    }
}

private fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
    var cursor: Cursor? = null
    return try {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        cursor = context.contentResolver.query(contentUri, proj, null, null, null)
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val path = cursor.getString(column_index)
        Log.e("TAG", "getRealPathFromURI Exception : $path")
        val exif = ExifInterface(path)
        Log.e("TAG", "getRealPathFromURI Exception : ${exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)}")
        exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)
        exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF)
        exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)
        exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF)

    } catch (e: Exception) {
        Log.e("TAG", "getRealPathFromURI Exception : $")
        ""
    } finally {
        cursor?.close()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLearnTheme {
        Greeting("Android")
    }
}

@Composable
fun CaptureBitmap(
    content: @Composable () -> Unit,
): () -> Bitmap {

    val context = LocalContext.current

    /**
     * ComposeView that would take composable as its content
     * Kept in remember so recomposition doesn't re-initialize it
     **/
    val composeView = remember { ComposeView(context) }

    /**
     * Callback function which could get latest image bitmap
     **/
    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    /** Use Native View inside Composable **/
    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )

    /** returning callback to bitmap **/
    return ::captureBitmap
}

fun storeBitmapInCache(context: Context, bitmap: Bitmap): String? {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "IMG_$timeStamp.png" // Unique file name
    val cacheDir = context.cacheDir // Get the app's cache directory
    val file = File(cacheDir, fileName)

    return try {
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos) // 100 is the highest quality
        fos.close()
        file.absolutePath // Return the file path if successful
    } catch (e: IOException) {
        e.printStackTrace()
        null // Return null on failure
    }
}
fun convertGPSLatitude(latitudeStr: String): Double? {
    try {
        val parts = latitudeStr.split(",")

        if (parts.size != 3) {
            return null
        }

        val degrees = parts[0].split("/")
        val minutes = parts[1].split("/")
        val seconds = parts[2].split("/")

        if (degrees.size != 2 || minutes.size != 2 || seconds.size != 2) {
            return null
        }

        val degreeValue = degrees[0].toDoubleOrNull()?.let { degrees[1].toDoubleOrNull()?.div(it) }
        val minuteValue = minutes[0].toDoubleOrNull()?.let { minutes[1].toDoubleOrNull()?.div(it) }
        val secondValue = seconds[0].toDoubleOrNull()?.let { seconds[1].toDoubleOrNull()?.div(it) }

        if (degreeValue == null || minuteValue == null || secondValue == null) {
            return null
        }

        val latitude = degreeValue + minuteValue / 60 + secondValue / 3600
        return if (latitudeStr.contains("S")) -latitude else latitude
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}
fun deleteFileFromCache(file: File) = if (file.exists()) file.delete() else false
