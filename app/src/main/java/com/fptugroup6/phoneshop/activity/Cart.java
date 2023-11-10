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
                    //getPhones(orderDetails);
                    //Log.d("product name", String.valueOf(order_details.get(0).getQuantity()));

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product_CartDetail>> call, Throwable t) {

            }
        });
    }

//    private void getPhones(ArrayList<OrderDetails> orderDetails) {
//        for(int i = 0; i < orderDetails.size();i++){
//            Log.d("size", String.valueOf(orderDetails.size()));
//            int quantity = orderDetails.get(i).getQuantity();
//            Call<Phone> call = apiService.GetPhone(orderDetails.get(i).getPhoneID());
//            call.enqueue(new Callback<Phone>() {
//                @Override
//                public void onResponse(Call<Phone> call, Response<Phone> response) {
//                    if(response.isSuccessful()){
//                        Phone phone = response.body();
//                        Product_CartDetail p = new Product_CartDetail();
//                        p.setImageUrl(phone.getImageUrl());
//                        p.setModelName(phone.getModelName());
//                        p.setQuantity(quantity);
//                        p.setPrice(phone.getPrice() * quantity);
//                        Log.d("Thứ tự sản phẩm","Sản phẩm" + phone.getPhoneId());
//                        Log.d("Phone name",phone.getModelName());
//                        Log.d("Phone url",phone.getImageUrl());
//                        Log.d("Phone quannity",String.valueOf(quantity));
//                        Log.d("Phone price",String.valueOf(phone.getPrice()));
//                        order_details.add(p);
//                    }else{
//                        Log.e("Phone", "Get Phone failed");
//                        Toast.makeText(getApplicationContext(),"Get Data: Fail ", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Phone> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//    private Phone GetPhone(){
//        Call<Phone> call = apiService.GetPhone(11);
//        call.enqueue(new Callback<Phone>() {
//            @Override
//            public void onResponse(Call<Phone> call, Response<Phone> response) {
//                if(response.isSuccessful()){
//                    phone = response.body();
//                }else{
//
//                }
//
//            }
//            @Override
//            public void onFailure(Call<Phone> call, Throwable t) {
//            }
//        });
//        return phone;
//    }
//    private ArrayList<Product_CartDetail> getProductCartDetails(ArrayList<Phone> phones_raw) {
//        ArrayList<Product_CartDetail> productCartDetails1 = new ArrayList<>();
//        productCartDetails1.add(new Product_CartDetail("https://drive.google.com/uc?export=download&id=1Gx78jG3hSzNgwuN5pO84F3v58Ma1jy3b\\r\\n",phones_raw.get(0).getModelName(),1000000,"san pham dai ha gia", 5));
//        productCartDetails1.add(new Product_CartDetail("https://drive.google.com/uc?export=download&id=1Gx78jG3hSzNgwuN5pO84F3v58Ma1jy3b\\r\\n","Iphone11",1000000,"san pham dai ha gia", 5));
//        productCartDetails1.add(new Product_CartDetail("https://drive.google.com/uc?export=download&id=1Gx78jG3hSzNgwuN5pO84F3v58Ma1jy3b\\r\\n","Iphone11",1000000,"san pham dai ha gia", 5));
//        productCartDetails1.add(new Product_CartDetail("https://drive.google.com/uc?export=download&id=1Gx78jG3hSzNgwuN5pO84F3v58Ma1jy3b\\r\\n","Iphone11",1000000,"san pham dai ha gia", 5));
//        productCartDetails1.add(new Product_CartDetail("https://drive.google.com/uc?export=download&id=1Gx78jG3hSzNgwuN5pO84F3v58Ma1jy3b\\r\\n","Iphone11",1000000,"san pham dai ha gia", 5));
//        productCartDetails1.add(new Product_CartDetail("https://drive.google.com/uc?export=download&id=1Gx78jG3hSzNgwuN5pO84F3v58Ma1jy3b\\r\\n","Iphone11",1000000,"san pham dai ha gia", 5));
//        return productCartDetails1;
//    }
        private void display_recycler_view(ArrayList<Product_CartDetail> list) {
            RecyclerView rv = findViewById(R.id.recyclerview_cart);
            AdapterCart adapterCart = new AdapterCart(list, Cart.this );
            rv.setLayoutManager(new GridLayoutManager(Cart.this, 1));
            rv.setAdapter(adapterCart);
        }

}