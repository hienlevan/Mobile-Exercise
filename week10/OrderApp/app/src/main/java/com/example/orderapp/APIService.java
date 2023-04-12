package com.example.orderapp;

import android.os.Message;

import java.util.List;

import com.example.orderapp.Model.Category;
import com.example.orderapp.Model.ImageUpload;
import com.example.orderapp.Model.LastProduct;
import com.example.orderapp.Model.ProductDetailTrue;
import com.example.orderapp.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    public static final String BASE_URL = "http://app.iotstar.vn/appfoods/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    APIService API_SERVICE = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();
    @GET("lastproduct.php")
    Call<List<LastProduct>> getLastProductAll();
    @Multipart
    @POST("getcategory.php")
    Call<List<LastProduct>> getCategoryItem(@Part(Const.MY_IDCATEGORY)RequestBody idcategory);
    @Multipart
    @POST("newmealdetail.php")
    Call<ProductDetailTrue> getMealDetail(@Part(Const.MY_GETMEAL)RequestBody id);
    @Multipart
    @POST("upload.php")
    Call<List<ImageUpload>> upload(@Part(Const.MY_USERNAME)RequestBody username,
                                   @Part MultipartBody.Part part);
    @Multipart
    @POST("upload1.php")
    Call<Message> upload1(@Part(Const.MY_USERNAME)RequestBody username,
                          @Part MultipartBody.Part part);
    @Multipart
    @POST("updateimages.php")
    Call<List<User>> updateimages(@Part(Const.MY_USERNAME)RequestBody id, @Part MultipartBody.Part images);

}
