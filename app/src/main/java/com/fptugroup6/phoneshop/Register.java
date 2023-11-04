package com.fptugroup6.phoneshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void signup(View view) {
        startActivity(new Intent(Register.this,MainActivity.class));
    }
    public void login(View view){
        startActivity(new Intent(Register.this,Login.class));
    }
}
