package com.example.composelearn.musicapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilApiImageView(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String = "Api Url Image",
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(false)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = Modifier.then(modifier)
    )
}