package com.example.composelearn.mypushnotification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.data.isNotEmpty()) {
            val title = message.data["title"]
            val body = message.data["body"]
            val deepLink = message.data["deepLink"] ?: ""
            val imageUrl = message.data["imageUrl"] ?: ""


        }
    }
}