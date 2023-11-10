package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.model.msgModelClass;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Button btnHome = findViewById(R.id.btnHome);
        Button btnSendMessage = findViewById(R.id.btnSendMessage);
        EditText txtMessage = findViewById(R.id.txtMessage);
        // Button Home Clicked
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Button Send Message Clicked
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}