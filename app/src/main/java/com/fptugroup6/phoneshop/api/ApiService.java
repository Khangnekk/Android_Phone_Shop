package com.fptugroup6.phoneshop.api;

import com.fptugroup6.phoneshop.model.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/Login")
    Call<Account> login(@Body Account account);

}
