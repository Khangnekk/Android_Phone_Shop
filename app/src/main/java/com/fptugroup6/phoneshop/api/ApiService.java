package com.fptugroup6.phoneshop.api;

import com.fptugroup6.phoneshop.model.Account;
import com.fptugroup6.phoneshop.model.OrderDetails;
import com.fptugroup6.phoneshop.model.Phone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("api/Login")
    Call<Account> login(@Body Account account);

    @POST("api/register")
    Call<Account> register(@Body Account account);
    @GET("api/product/getphone")
    Call<Phone> GetPhone(@Query("username") int phone_id);
    @GET("api/phone")
    Call<ArrayList<Phone>> phone();
    @GET("api/OderCart")
    Call<ArrayList<OrderDetails>> GetOrderDetail(@Query("username") String username);
    @POST("api/AddToCart")
    Call<Boolean> AddToCart(@Query("username") String username,@Query("phoneid") String phone_id,@Query("quantity") String quantity);
}
