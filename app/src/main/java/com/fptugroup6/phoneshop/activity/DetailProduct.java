package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Phone;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<String> listString = new ArrayList<>();

    private Button increment;
    private Button decrement;

    private LinearLayout addToCart;
    private LinearLayout buyNow;
    private EditText amout;
    private ImageView imageProduct;
    int number = 1;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        apiService = ApiClient.getClient().create(ApiService.class);

        Intent intent = getIntent();
        Phone phone = (Phone) intent.getSerializableExtra("phone");
//        phone = (Phone) intent.getSerializableExtra("phone");

        rv = findViewById(R.id.recycler_view);
        listString = setData(phone);
        Adapter adapter = new Adapter(listString, this);
        rv.setLayoutManager(new GridLayoutManager(this, 1));
        rv.setAdapter(adapter);

        increment = findViewById(R.id.incrementButton);
        decrement = findViewById(R.id.decrementButton);

        addToCart = findViewById(R.id.add_cart);
        buyNow = findViewById(R.id.buy_now);

        amout = findViewById(R.id.amout);
        amout.setText(String.valueOf(number));



        imageProduct = findViewById(R.id.image_product);
        Picasso.get().load(phone.getImageUrl()).into(imageProduct);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                amout.setText(String.valueOf(number));

            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number--;
                amout.setText(String.valueOf(number));
            }
        });

        // Add to carrt
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Call<Boolean> call = apiService.AddToCart("Khang","1","2");
//                call.enqueue(new Callback<Boolean>() {
//                    @Override
//                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Toast.makeText(DetailProduct.this, "Bạn vừa add 1 sản phẩm vào giỏ hàng", Toast.LENGTH_LONG);
                        MyNotificationChanel.sendNotification(DetailProduct.this, Cart.class);
//                    }
//
//                    @Override
//                    public void onFailure(Call<Boolean> call, Throwable t) {
//                        Toast.makeText(DetailProduct.this, "add failed", Toast.LENGTH_LONG).show();
//                    }
//                });
//
//
            }
        });
    }

    private ArrayList<String> setData(Phone phone) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Model Name: "+phone.getModelName());
        list.add("Manufacturer: "+phone.getManufacturer());
        String formattedNumber = addThousandSeparators(String.valueOf(phone.getPrice()));
        list.add("Price: "+formattedNumber+" VNĐ");
        list.add("In stock: "+(phone.isInStock()?"Yes":"No"));
        list.add("Description: "+phone.getDescription());
        return list;
    }

    public static String addThousandSeparators(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int length = input.length();
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = length - 1; i >= 0; i--) {
            result.insert(0, input.charAt(i));
            count++;

            if (count % 3 == 0 && i > 0) {
                result.insert(0, ",");
            }
        }

        return result.toString();
    }
}