package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.KeyCycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Order;
import com.fptugroup6.phoneshop.model.OrderDetails;
import com.fptugroup6.phoneshop.model.Phone;
import com.fptugroup6.phoneshop.model.Product_CartDetail;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {

    ApiService apiService;
    Phone phone;
    ArrayList<OrderDetails> oderOrderDetails;
    ArrayList<Phone> phones = new ArrayList<>();

    ArrayList<Product_CartDetail> order_details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        apiService = ApiClient.getClient().create(ApiService.class);
        get_phone_cart_detail();
    }
    private void get_phone_cart_detail(){
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
        RecyclerView rv = findViewById(R.id.recyclerview_cart);
        AdapterCart adapterCart = new AdapterCart(list, Cart.this );
        rv.setLayoutManager(new GridLayoutManager(Cart.this, 1));
        rv.setAdapter(adapterCart);
    }

}