package com.example.easychat;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMNotificationService extends FirebaseMessagingService {

    private static final String TAG = "FCMNotificationService";

    @Override
    public void onNewToken( String token) {
        super.onNewToken(token);
        Log.d("FCMToken", "New token: " + token);
        // Send the token to your server or save it as needed
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("FCMMessage", "Message received from: " + remoteMessage.getFrom());
    }


    private void sendRegistrationToServer(String token) {
        // Implement this method to send the token to your server
        Log.d(TAG, "Sending token to server: " + token);
        // For example, you could use an HTTP client to send the token to your server
    }
}
