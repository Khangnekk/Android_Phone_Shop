package com.fptugroup6.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Phone;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SelectListener {
    ApiService apiService;
//    public List<Phone> phones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recyclerviewMain);
        apiService = ApiClient.getClient().create(ApiService.class);
        ArrayList<Phone> PhonesList = getPhones();
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