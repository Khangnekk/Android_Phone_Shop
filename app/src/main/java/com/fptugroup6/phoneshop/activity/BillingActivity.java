package com.fptugroup6.phoneshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Account;
import com.fptugroup6.phoneshop.model.Link;
import com.fptugroup6.phoneshop.model.PaymentInformationModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;

public class BillingActivity extends AppCompatActivity {
    ApiService apiService;
    PaymentInformationModel pim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiService = ApiClient.getClient().create(ApiService.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        TextView ProductName = findViewById(R.id.txtProductName);
        TextView Amount = findViewById(R.id.txtAmount);
        String txtProductName = ProductName.getText().toString();
        Log.e("PIM1", txtProductName);
        String txtAmount = Amount.getText().toString().replace(".", "").trim();
        Button btnPayment = findViewById(R.id.btnConfirm);

        PaymentInformationModel pim = new PaymentInformationModel();
        pim.setOrderType("OP_BUY");
        pim.setName(txtProductName);
        pim.setAmount(Long.parseLong(txtAmount));
        pim.setOrderDescription("Pay for smartphone purchases");


        btnPayment.setOnClickListener(new View.OnClickListener() {
            Call<Link> call = apiService.createPayment(pim);
            String dummy = "https: //sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_Amount=1200000000&vnp_Command=pay&vnp_CreateDate=20231109073440&vnp_CurrCode=VND&vnp_IpAddr=192.168.1.3&vnp_Locale=vn&vnp_OrderInfo=ProductName+Mua+hang+12000000&vnp_OrderType=OP_BUY&vnp_ReturnUrl=http%3A%2F%2F192.168.1.3%3A7017%2Fapi%2Fvnpay&vnp_TmnCode=G6A4MW0R&vnp_TxnRef=638351120800499465&vnp_Version=2.1.0&vnp_SecureHash=5b20f61a22d41e4b4171750bae23fd8d032c380de535c70934b52b6d1b7c18c46b3fad060970e1382088597161056547e38c00bb1d643adaa7df6c039752e0cf";
            @Override
            public void onClick(View v) {
                call.enqueue(new Callback<Link>() {
                    @Override
                    public void onResponse(Call<Link> call, Response<Link> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent();
                            String apiUrl = response.body().getValue();
                            intent.putExtra("url", apiUrl);
                            intent.setClass(BillingActivity.this, Payment.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Link> call, Throwable t) {
//                        Log.e("PIM1X", t.getMessage());
                    }
                });
            }
        });
    }
}
