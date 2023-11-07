package com.fptugroup6.phoneshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.dal.AccountDBContext;
import com.fptugroup6.phoneshop.model.Account;
import android.os.Handler;
import android.os.Looper;


import java.io.Serializable;

public class Login extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        handler = new Handler(Looper.getMainLooper());

        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtPassword = findViewById(R.id.txtPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String username = txtUsername.
            }
        });
    }
}
