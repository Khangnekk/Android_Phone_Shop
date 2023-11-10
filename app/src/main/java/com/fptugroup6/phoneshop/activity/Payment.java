package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;

public class Payment extends AppCompatActivity {

//    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_webview);
        WebView webView = findViewById(R.id.webViewPayment);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        // Load URL
        webView.loadUrl(url);
        Button btnHomePayment = findViewById(R.id.btnHomePayment);
        btnHomePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Payment.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btnChatPayment = findViewById(R.id.btnChatPayment);
        btnChatPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Payment.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        Button btnCart = findViewById(R.id.btnMapPayment);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Payment.this, Cart.class);
                startActivity(intent);
            }
        });
    }
}
