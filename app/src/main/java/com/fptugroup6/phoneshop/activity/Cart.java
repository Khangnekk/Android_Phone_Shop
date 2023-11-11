package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Link;
import com.fptugroup6.phoneshop.model.PaymentInformationModel;
import com.fptugroup6.phoneshop.model.Product_CartDetail;
import com.fptugroup6.phoneshop.session.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {

    ApiService apiService;
    static TextView total_payment;
    TextView payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        apiService = ApiClient.getClient().create(ApiService.class);

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Cart.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnChat = findViewById(R.id.btnChat);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Cart.this, ChatActivity.class);
                startActivity(intent);
            }
        });


        TextView payment;
        payment = findViewById(R.id.btnPaymentAll);
        MySharedPreferences sharedPreferences = MySharedPreferences.getInstance(getApplicationContext());
        payment.setOnClickListener(new View.OnClickListener() {

            //            Call<Link> call = apiService.createPayment(pim);
            @Override
            public void onClick(View v) {
                TextView total_payment;

                total_payment = findViewById(R.id.Totalnek);

                String txtAmount = total_payment.getText().toString().replace(".", "").trim();
                PaymentInformationModel pim = new PaymentInformationModel();
                pim.setOrderType("OP_BUY");
                pim.setName("Payment all cart");
                Log.e("TestLong", txtAmount);
                pim.setAmount(Long.parseLong(txtAmount));
                pim.setOrderDescription("Pay for smartphone purchases");
                Call<Link> call = apiService.createPayment(pim);
                call.enqueue(new Callback<Link>() {
                    @Override
                    public void onResponse(Call<Link> call, Response<Link> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent();
                            String apiUrl = response.body().getValue();
                            intent.putExtra("url", apiUrl);
                            intent.setClass(Cart.this, Payment.class);
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

        get_phone_cart_detail(sharedPreferences);
    }
    private void get_phone_cart_detail(MySharedPreferences share){
        Call<ArrayList<Product_CartDetail>> call = apiService.GetOrderDetail(share.getData("Username", ""));
        Log.d("username in cart",share.getData("Username", ""));
        //Call<ArrayList<Product_CartDetail>> call = apiService.GetOrderDetail("Khang");
        call.enqueue(new Callback<ArrayList<Product_CartDetail>>() {
            @Override
            public void onResponse(Call<ArrayList<Product_CartDetail>> call, Response<ArrayList<Product_CartDetail>> response) {
                if (response.body() != null) {
                    ArrayList<Product_CartDetail> orderDetails = response.body();
                    display_recycler_view(orderDetails);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product_CartDetail>> call, Throwable t) {

            }
        });
    }

    private void display_recycler_view(ArrayList<Product_CartDetail> list) {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getQuantity() * list.get(i).getPrice();
        }
        TextView total_payment = findViewById(R.id.Totalnek);
        total_payment.setText(String.valueOf(total));
        RecyclerView rv = findViewById(R.id.recyclerview_cart);
        AdapterCart adapterCart = new AdapterCart(list, Cart.this);
        rv.setLayoutManager(new GridLayoutManager(Cart.this, 1));
        rv.setAdapter(adapterCart);
    }

    public static void displayTotal(long total){
        total_payment.setText(String.valueOf(total));
    }

}