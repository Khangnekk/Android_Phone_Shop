package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Phone;
import com.fptugroup6.phoneshop.session.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SelectListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_demo_bill) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),BillingActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.option_map) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),Map_Activity.class);
            startActivity(intent);
        }
        return true;
    }

    ApiService apiService;

    //SharedPreferences sharedPreferences =  getSharedPreferences("session", Context.MODE_PRIVATE);
//    public List<Phone> phones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recyclerviewMain);
        apiService = ApiClient.getClient().create(ApiService.class);
        ArrayList<Phone> PhonesList = getPhones();
    }

    private void set_Session(String username){
        SharedPreferences sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username); // Replace "key" and "value" with your own data
        editor.apply();
    }

    private ArrayList<Phone> getPhones() {
        ArrayList<Phone> phones = new ArrayList<Phone>();
        Call<ArrayList<Phone>> call = apiService.phone();
        call.enqueue(new Callback<ArrayList<Phone>>() {
            @Override
            public void onResponse(Call<ArrayList<Phone>> call, Response<ArrayList<Phone>> response) {
                if(response.body()!=null){
                    Toast.makeText(getApplicationContext(),"Get Data Success: ", Toast.LENGTH_LONG).show();
                    ArrayList<Phone> phones = response.body();
                    processPhoneData(phones);
                }else{
                    Toast.makeText(getApplicationContext(),"Get Data: Fail ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Phone>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        return phones;
    }

    private void processPhoneData(ArrayList<Phone> phones) {
        RecyclerView rv = findViewById(R.id.recyclerviewMain);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(new ListProductAdapter(getApplicationContext(), phones, this));
    }

    @Override
    public void OnItemClicked(Phone item) {
        Intent intent2 = new Intent();
        intent2.putExtra("phone",item);
        intent2.setClass(MainActivity.this,DetailProduct.class);
        startActivity(intent2);
    }
}