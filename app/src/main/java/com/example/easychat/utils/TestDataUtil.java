package com.example.easychat.utils;

import android.util.Log;

import com.example.easychat.model.ChatMessageModel;
import com.example.easychat.model.ChatroomModel;
import com.example.easychat.model.UserModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

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
        List<UserModel> users = Arrays.asList(
                new UserModel("ironman", "Iron Man", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("spiderman", "Spider-Man", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("captainamerica", "Captain America", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("blackwidow", "Black Widow", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("hulk", "Hulk", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("thor", "Thor", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("scarletwitch", "Scarlet Witch", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("blackpanther", "Black Panther", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("doctorstrange", "Doctor Strange", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now()),
                new UserModel("antman", "Ant-Man", "https://i.imgur.com/6S4NNpm.jpg", Timestamp.now())
        );

        for (UserModel user : users) {
            if (user.getUserId() != null && !user.getUserId().isEmpty()) {
                usersCollection.document(user.getUserId()).set(user)
                        .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added user: " + user.getUserId()))
                        .addOnFailureListener(e -> Log.e(TAG, "Error adding user: " + user.getUserId(), e));
            } else {
                Log.e(TAG, "User ID for user is null or empty: " + user.getUserName());
            }
        }
    }

    private static void addChatrooms() {
        List<ChatroomModel> chatrooms = Arrays.asList(
                new ChatroomModel("chatroom1", Arrays.asList("ironman", "spiderman"), Timestamp.now(), "Hello, Spider-Man!"),
                new ChatroomModel("chatroom2", Arrays.asList("hulk", "thor"), Timestamp.now(), "Hey Thor!")
        );

        for (ChatroomModel chatroom : chatrooms) {
            if (chatroom.getChatroomId() != null && !chatroom.getChatroomId().isEmpty()) {
                db.collection("chatrooms").document(chatroom.getChatroomId()).set(chatroom)
                        .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added chatroom: " + chatroom.getChatroomId()))
                        .addOnFailureListener(e -> Log.e(TAG, "Error adding chatroom: " + chatroom.getChatroomId(), e));
            } else {
                Log.e(TAG, "Chatroom ID for chatroom is null or empty.");
            }
        }
    }

    private static void addChatMessages() {
        List<ChatMessageModel> messages = Arrays.asList(
                new ChatMessageModel("Hello, Spider-Man!", "ironman", Timestamp.now()),
                new ChatMessageModel("Hi, Iron Man!", "spiderman", Timestamp.now()),
                new ChatMessageModel("Hey Thor!", "hulk", Timestamp.now()),
                new ChatMessageModel("Hey Hulk!", "thor", Timestamp.now())
        );

        // Adding messages to chatroom1
        CollectionReference chatroom1Messages = db.collection("chatrooms").document("chatroom1").collection("messages");
        for (ChatMessageModel message : messages.subList(0, 2)) {
            if (message.getSenderId() != null && !message.getSenderId().isEmpty()) {
                chatroom1Messages.add(message)
                        .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added message to chatroom1"))
                        .addOnFailureListener(e -> Log.e(TAG, "Error adding message to chatroom1", e));
            } else {
                Log.e(TAG, "Message or its sender ID is null or empty.");
            }
        }

        // Adding messages to chatroom2
        CollectionReference chatroom2Messages = db.collection("chatrooms").document("chatroom2").collection("messages");
        for (ChatMessageModel message : messages.subList(2, 4)) {
            if (message.getSenderId() != null && !message.getSenderId().isEmpty()) {
                chatroom2Messages.add(message)
                        .addOnSuccessListener(aVoid -> Log.i(TAG, "Successfully added message to chatroom2"))
                        .addOnFailureListener(e -> Log.e(TAG, "Error adding message to chatroom2", e));
            } else {
                Log.e(TAG, "Message or its sender ID is null or empty.");
            }
        }
    }
}
