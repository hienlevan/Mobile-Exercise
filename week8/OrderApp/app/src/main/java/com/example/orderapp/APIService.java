package com.example.orderapp;

import java.util.List;
import com.example.orderapp.Category;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();
    @GET("lastproduct.php")
    Call<List<LastProduct>> getLastProductAll();
}
