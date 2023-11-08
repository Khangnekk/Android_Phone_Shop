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

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.model.Phone;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

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