package com.fptugroup6.phoneshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    ApiService apiService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        TextView textSignIn = findViewById(R.id.txtSignIn);

        TextView txtUsername = findViewById(R.id.txtSuUsername);
        TextView txtPassword = findViewById(R.id.txtSuPassword);
        TextView txtRePassword = findViewById(R.id.txtSuRepass);
        TextView txtEmail = findViewById(R.id.txtSuEmail);
        TextView txtFullName = findViewById(R.id.name);

        Button btnSignUp = findViewById(R.id.btnSignUp);

        apiService = ApiClient.getClient().create(ApiService.class);
        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String rePassword = txtRePassword.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String fullName = txtFullName.getText().toString().trim();
                if(password.equals(rePassword)){
                    Account accountRaw = new Account();
                    accountRaw.setUsername(username);
                    accountRaw.setPassword(password);
                    accountRaw.setFullName(fullName);
                    accountRaw.setEmail(email);
                    if(username!=null && password!=null && password != null)
                        clickBtnSignUp(accountRaw);
                    else
                        Toast.makeText(getApplicationContext(),"Please enter your information", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Password not match", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void login(View view){
        startActivity(new Intent(Register.this,Login.class));
    }

    public  void clickBtnSignUp(Account accountRaw){
        Call<Account> call = apiService.register(accountRaw);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                    Toast.makeText(getApplicationContext(),"SignUp Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setClass(Register.this, Login.class);
                    startActivity(intent);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
