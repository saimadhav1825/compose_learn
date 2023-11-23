package com.example.composelearn.mypushnotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.composelearn.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.random.Random

const val PUSH_NOTIFICATION_CHANNEL_ID = "my_notification_channel"
const val PUSH_NOTIFICATION_CHANNEL_NAME = "My FCM Notifications"
suspend fun sendNotification(
    title: String,
    body: String,
    imageUrl: String,
    deepLink: String,
    context: Context,
) {


    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val taskDetailIntent = Intent(
        Intent.ACTION_VIEW,
        deepLink.toUri(),
        context,
        MainActivity::class.java
    )


    val imageBitmap  = if (imageUrl.isNotBlank()) getBitmapFromUrl(imageUrl) else null

    val pendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(taskDetailIntent)
        getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )
    }


    val notificationBuilder = NotificationCompat.Builder(context, PUSH_NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(androidx.core.R.drawable.notification_bg)
        .setContentTitle(title)
        .setContentText(body)
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)

    if (imageBitmap != null){
        notificationBuilder.setLargeIcon(imageBitmap)
    }

    notificationManager.notify(Random.nextInt(), notificationBuilder.build())
}

private suspend fun getBitmapFromUrl(imageUrl:String) : Bitmap?{

    return withContext(Dispatchers.IO) {
        try {
            val url = URL(imageUrl)
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            null
        }
    }

}