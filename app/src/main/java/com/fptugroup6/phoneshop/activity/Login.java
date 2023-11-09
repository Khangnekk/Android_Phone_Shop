package com.fptugroup6.phoneshop.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Account;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtPassword = findViewById(R.id.txtPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        apiService = ApiClient.getClient().create(ApiService.class);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                Account accountRaw = new Account();
                accountRaw.setUsername(username);
                accountRaw.setPassword(password);
                clickBtnSignIn(accountRaw);
            }
        });
    }

    public  void clickBtnSignIn(Account accountRaw){
        Call<Account> call = apiService.login(accountRaw);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                try{
                    if(response.isSuccessful()){
                        SharedPreferences sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Username", response.body().getUsername()); // Replace "key" and "value" with your own data
                        editor.apply();
                        if(response.body().getUsername()!=null){
                            Toast.makeText(getApplicationContext(),"Login Success: "+ response.body().getEmail(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.setClass(Login.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Username or password not match", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Login Fail", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Login Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
                Log.e("LoginStatus", t.toString());
            }
        });
    }

    public void signup(View view) {
        startActivity(new Intent(Login.this,Register.class));
    }
}
