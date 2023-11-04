package com.fptugroup6.phoneshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DetailProduct extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<String> listString = new ArrayList<>();

    private Button increment;
    private Button decrement;

    private LinearLayout addToCart;
    private LinearLayout buyNow;
    private EditText amout;
    int number = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        rv = findViewById(R.id.recycler_view);
        listString = setData();
        Adapter adapter = new Adapter(listString, this);
        rv.setLayoutManager(new GridLayoutManager(this,1));
        rv.setAdapter(adapter);

        increment = findViewById(R.id.incrementButton);
        decrement = findViewById(R.id.decrementButton);

        addToCart = findViewById(R.id.add_cart);
        buyNow = findViewById(R.id.buy_now);

        amout = findViewById(R.id.amout);
        amout.setText(String.valueOf(number));



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

    private ArrayList<String> setData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Iphone 11 Promax bản Hàn.");
        list.add("Màu tím.");
        list.add("Màn hình 14 inch.");
        list.add("Pin 6000mAh.");
        list.add("Giá: 4.000.000.000.");
        list.add("Bộ nhớ: 1T.");
        list.add("Có nhiều tính năng nổi bật.");
        list.add("Máy mới đập hộp 100%.");
        list.add("Bảo hành 6 tháng lỗi đổi mới trong 3 tháng.");
        list.add("Có thể trả góp 0%.");
        list.add("Thiết kế sang trọng, sở hữu gam màu thời thượng khơi dậy những ngày mới của bạn.");
        list.add("Giảm giá 60%.");
        return list;
    }
}