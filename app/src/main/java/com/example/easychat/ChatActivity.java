package com.example.easychat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easychat.adapter.MessageRecyclerAdapter;
import com.example.easychat.model.ChatMessageModel;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private MessageRecyclerAdapter messageAdapter;
    private List<ChatMessageModel> messageList;
    private EditText chatMessageInput;
    private ImageButton messageSendBtn;
    private TextView otherUsername;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get data from Intent
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        String userName = intent.getStringExtra("userName");

        // Initialize views
        chatRecyclerView = findViewById(R.id.chat_recycler_view);
        chatMessageInput = findViewById(R.id.chat_message_input);
        messageSendBtn = findViewById(R.id.message_send_btn);
        otherUsername = findViewById(R.id.other_username);

        // Set other user's name
        otherUsername.setText(userName);

        // Set up RecyclerView
        messageList = new ArrayList<>();
        messageAdapter = new MessageRecyclerAdapter(messageList);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(messageAdapter);

        // Load messages (example)
        loadMessages();

        // Send message button click listener
        messageSendBtn.setOnClickListener(v -> {
            String messageText = chatMessageInput.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
            }
        });
    }

    private void loadMessages() {
        // Add code to load messages from the database
        // For now, adding some dummy messages
        messageList.add(new ChatMessageModel("Hello!", "user1"));
        messageList.add(new ChatMessageModel("Hi there!", "user2"));
        messageAdapter.notifyDataSetChanged();
    }

    private void sendMessage(String messageText) {
        // Add code to send message to the database
        // For now, adding the message to the list
        messageList.add(new ChatMessageModel(messageText, "currentUserId"));
        messageAdapter.notifyDataSetChanged();
        chatMessageInput.setText("");
        chatRecyclerView.scrollToPosition(messageList.size() - 1);
    }
}
