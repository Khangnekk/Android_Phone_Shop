package com.fptugroup6.phoneshop.api;

import com.fptugroup6.phoneshop.model.Account;
import com.fptugroup6.phoneshop.model.Link;
import com.fptugroup6.phoneshop.model.PaymentInformationModel;
import com.fptugroup6.phoneshop.model.OrderDetails;
import com.fptugroup6.phoneshop.model.Phone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/Login")
    Call<Account> login(@Body Account account);

    @POST("api/register")
    Call<Account> register(@Body Account account);

    @GET("api/phone")
    Call<ArrayList<Phone>> phone();

    @POST("api/VNPAY")
    Call<Link> createPayment(@Body PaymentInformationModel paymentInformationModel);
//@POST("api/VNPAY/create-payment")
//Call<String> createPayment(@Body PaymentInformationModel paymentInformationModel);
    @GET("api/OderCart")
    Call<ArrayList<OrderDetails>> GetOrderDetail(String username);
    @POST("api/AddToCart")
    Call<Boolean> AddToCart(@Body String username, String phoneid, String quantity);
}
