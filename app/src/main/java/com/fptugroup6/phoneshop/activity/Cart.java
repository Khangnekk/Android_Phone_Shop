package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.KeyCycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Order;
import com.fptugroup6.phoneshop.model.OrderDetails;
import com.fptugroup6.phoneshop.model.Phone;
import com.fptugroup6.phoneshop.model.Product_CartDetail;
import com.fptugroup6.phoneshop.session.MySharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {

    ApiService apiService;
    TextView total_payment;
    TextView payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        apiService = ApiClient.getClient().create(ApiService.class);
        MySharedPreferences sharedPreferences = MySharedPreferences.getInstance(getApplicationContext());

        total_payment = findViewById(R.id.Total);
        payment = findViewById(R.id.payment_button);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        get_phone_cart_detail(sharedPreferences);
    }
    private void get_phone_cart_detail(MySharedPreferences share){
        //Call<ArrayList<Product_CartDetail>> call = apiService.GetOrderDetail(share.getData("Username", ""));
        Call<ArrayList<Product_CartDetail>> call = apiService.GetOrderDetail("Khang");
        call.enqueue(new Callback<ArrayList<Product_CartDetail>>() {
            @Override
            public void onResponse(Call<ArrayList<Product_CartDetail>> call, Response<ArrayList<Product_CartDetail>> response) {
                if(response.body()!= null){
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
        for(int i = 0; i < list.size();i++){
            total += list.get(i).getQuantity() * list.get(i).getPrice();
        }
        TextView total_payment = findViewById(R.id.Total);
        total_payment.setText(String.valueOf(total));
        RecyclerView rv = findViewById(R.id.recyclerview_cart);
        AdapterCart adapterCart = new AdapterCart(list, Cart.this );
        rv.setLayoutManager(new GridLayoutManager(Cart.this, 1));
        rv.setAdapter(adapterCart);
    }

}