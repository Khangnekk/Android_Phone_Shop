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
                Log.e("Accountx","OK");
                if (txtUsername.getText() != null && txtPassword.getText() != null) {
                    // Tạo một luồng mới để thực hiện công việc nặng
                    Log.e("Accountx","OK2");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("Accountx","OK3");
                            AccountDBContext accountDBContext = new AccountDBContext();
                            Log.e("Accountx","OK4");
                            final Account account = accountDBContext.getAccount(txtUsername.getText().toString(), txtPassword.getText().toString());
                            Log.e("Accountx",account.getFullName());
                            if (account == null) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Account doesn't exist", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(Login.this, MainActivity.class);
//                                        intent.putExtra("account", account);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter Username & Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
