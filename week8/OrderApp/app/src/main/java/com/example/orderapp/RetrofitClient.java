package com.example.orderapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient extends BaseClient {
    private static final String BASE_URL = "http://app.iotstar.vn/appfoods/";
    private static  APIService apiService;
    public static APIService getInstance(){
        if(apiService == null){
            return createService(APIService.class,BASE_URL);
        }
        return apiService;
    }
}
