package com.example.easychat.utils;

import android.util.Log;

import com.example.easychat.model.ChatMessageModel;
import com.example.easychat.model.ChatroomModel;
import com.example.easychat.model.UserModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class TestDataUtil {

    private static final String TAG = "TestDataUtil";

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static CollectionReference usersCollection = db.collection("users");

    public static void addTestData() {
        addUsers();
        addChatrooms();
        addChatMessages();
    }

    private static void addUsers() {
        UserModel user1 = new UserModel("user1", "User One", "https://example.com/user1.jpg");
        UserModel user2 = new UserModel("user2", "User Two", "https://example.com/user2.jpg");

        if (user1.getUserId() != null && !user1.getUserId().isEmpty()) {
            usersCollection.document(user1.getUserId()).set(user1)
                    .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added user1"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error adding user1", e));
        } else {
            Log.e(TAG, "User ID for user1 is null or empty.");
        }

        if (user2.getUserId() != null && !user2.getUserId().isEmpty()) {
            usersCollection.document(user2.getUserId()).set(user2)
                    .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added user2"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error adding user2", e));
        } else {
            Log.e(TAG, "User ID for user2 is null or empty.");
        }
    }


    private static void addChatrooms() {
        ChatroomModel chatroom1 = new ChatroomModel("chatroom1",
                Arrays.asList("user1", "user2"), Timestamp.now(), "Hello, User Two!");

        ChatroomModel chatroom2 = new ChatroomModel("chatroom2",
                Arrays.asList("user2", "user1"), Timestamp.now(), "Hello, User One!");

        if (chatroom1.getChatroomId() != null && !chatroom1.getChatroomId().isEmpty()) {
            db.collection("chatrooms").document(chatroom1.getChatroomId()).set(chatroom1)
                    .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added chatroom1"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error adding chatroom1", e));
        } else {
            Log.e(TAG, "Chatroom ID for chatroom1 is null or empty.");
        }

        if (chatroom2.getChatroomId() != null && !chatroom2.getChatroomId().isEmpty()) {
            db.collection("chatrooms").document(chatroom2.getChatroomId()).set(chatroom2)
                    .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added chatroom2"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error adding chatroom2", e));
        } else {
            Log.e(TAG, "Chatroom ID for chatroom2 is null or empty.");
        }
    }

    private static void addChatMessages() {
        ChatMessageModel message1 = new ChatMessageModel("Hello, User Two!", "user1", Timestamp.now());
        ChatMessageModel message2 = new ChatMessageModel("Hi, User One!", "user2", Timestamp.now());

        if (message1 != null && message1.getSenderId() != null && !message1.getSenderId().isEmpty()) {
            db.collection("chatrooms").document("chatroom1").collection("messages").add(message1)
                    .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added message1"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error adding message1", e));
        } else {
            Log.e(TAG, "Message1 or its sender ID is null or empty.");
        }

        if (message2 != null && message2.getSenderId() != null && !message2.getSenderId().isEmpty()) {
            db.collection("chatrooms").document("chatroom1").collection("messages").add(message2)
                    .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added message2"))
                    .addOnFailureListener(e -> Log.e(TAG, "Error adding message2", e));
        } else {
            Log.e(TAG, "Message2 or its sender ID is null or empty.");
        }
    }
}
