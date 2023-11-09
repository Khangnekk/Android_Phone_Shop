package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.KeyCycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ArrayList<Phone> productCartDetails = new ArrayList<>();
        RecyclerView rv = findViewById(R.id.recyclerview_cart);
        Call<ArrayList<OrderDetails>> call = apiService.GetOrderDetail("Khang");
        call.enqueue(new Callback<ArrayList<OrderDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderDetails>> call, Response<ArrayList<OrderDetails>> response) {
                if(response.body()!=null){
                    Toast.makeText(getApplicationContext(),"Get Data Success: ", Toast.LENGTH_LONG).show();
                    ArrayList<OrderDetails> oderOrderDetails = response.body();
                    ArrayList<Phone> productCartDetails = getPhone(oderOrderDetails);
                    setRecyclerView(productCartDetails);
                    //AdapterCart adapterCart = new AdapterCart()
                }else{
                    Toast.makeText(getApplicationContext(),"Get Data: Fail ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OrderDetails>> call, Throwable t) {

            }


        });
    }

    public void setRecyclerView(ArrayList<Phone> phones){
        RecyclerView rv = findViewById(R.id.recyclerview_cart);
        AdapterCart adapterCart = new AdapterCart(phones,Cart.this);
        rv.setLayoutManager(new GridLayoutManager(Cart.this, 1));
        rv.setAdapter(adapterCart);
    }
    public ArrayList<Phone> getPhone(ArrayList<OrderDetails> oderOrderDetails){
        ArrayList<Phone> phones = new ArrayList<>();
        for(int i = 0; i < oderOrderDetails.stream().count(); i++) {
            Call<Phone> phone = apiService.GetPhone(oderOrderDetails.get(i).getPhoneID());
            phone.enqueue(new Callback<Phone>() {
                @Override
                public void onResponse(Call<Phone> call, Response<Phone> response) {
                    phones.add(response.body());
                }

                @Override
                public void onFailure(Call<Phone> call, Throwable t) {

                }
            });
        }
        return phones;
    }
}