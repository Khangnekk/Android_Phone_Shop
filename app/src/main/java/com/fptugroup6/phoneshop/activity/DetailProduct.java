package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Link;
import com.fptugroup6.phoneshop.model.PaymentInformationModel;
import com.fptugroup6.phoneshop.model.Phone;
import com.fptugroup6.phoneshop.session.MySharedPreferences;
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

        Button btnChat = findViewById(R.id.btnChatDp1);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DetailProduct.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        Button btnHome = findViewById(R.id.btnHomeDp1);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DetailProduct.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnCart = findViewById(R.id.btnMapDp1);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DetailProduct.this, Cart.class);
                startActivity(intent);
            }
        });
        apiService = ApiClient.getClient().create(ApiService.class);
        MySharedPreferences mySharedPreferences = MySharedPreferences.getInstance(getApplicationContext());

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


        Toast.makeText(this, mySharedPreferences.getData("Username",""), Toast.LENGTH_SHORT).show();
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

        PaymentInformationModel pim = new PaymentInformationModel();
        pim.setOrderType("OP_BUY");
        pim.setName("Payment for "+phone.getModelName());
        pim.setAmount(phone.getPrice());
        pim.setOrderDescription("Pay for smartphone purchases");

        buyNow.setOnClickListener(new View.OnClickListener() {
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
                            intent.setClass(DetailProduct.this, Payment.class);
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

        // Add to carrt
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Boolean> call = apiService.AddToCart(mySharedPreferences.getData("Username","")
                        ,phone.getPhoneId()
                        ,Integer.parseInt(amout.getText().toString()));

                Log.d("username in Add to cart",mySharedPreferences.getData("Username", ""));

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(DetailProduct.this, "Bạn vừa add 1 sản phẩm vào giỏ hàng", Toast.LENGTH_LONG);
                            sendNotification();
                        }else{
                            Toast.makeText(DetailProduct.this, "Thêm sản phẩm thất bại", Toast.LENGTH_LONG);

                        }
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(DetailProduct.this, "add failed", Toast.LENGTH_LONG).show();
                    }
                });


            }
        }
        );}
    public void sendNotification() {
        Intent intent = new Intent(DetailProduct.this, Cart.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(DetailProduct.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(DetailProduct.this, MyNotificationChanel.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Shop Phone")
                .setContentText("Giỏ hàng bạn có sản phẩm")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that fires when the user ta
                // s the notification.
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(DetailProduct.this);

        // notificationId is a unique int for each notification that you must define.
        if (ActivityCompat.checkSelfPermission(DetailProduct.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        notificationManager.notify(1, builder.build());
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