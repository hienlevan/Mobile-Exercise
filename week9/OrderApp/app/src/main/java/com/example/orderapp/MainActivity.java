package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView name;
    ImageView image;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;


    RecyclerView recyclerView2;
    LastProductAdapter lastProductAdapter;
    List<LastProduct> lastProductList;

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();
        GetLastProduct();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){

            name = (TextView) findViewById(R.id.textView4);
            image = (ImageView) findViewById(R.id.imageView);
            User user = SharedPrefManager.getInstance(this).getUser();
            name.setText(user.getName());
        }else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
    private void AnhXa(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        categoryList=new ArrayList<>();
        lastProductList = new ArrayList<>();
        linearLayout = (LinearLayout) findViewById(R.id.Setting_Btn);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });
    }
    private void GetCategory(){
        apiService = RetrofitClient.getInstance();
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter =  new CategoryAdapter(MainActivity.this, categoryList);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                }else{
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetLastProduct(){
        apiService = RetrofitClient.getInstance();
        apiService.getLastProductAll().enqueue(new Callback<List<LastProduct>>() {
            @Override
            public void onResponse(Call<List<LastProduct>> call, Response<List<LastProduct>> response) {
                if(response.isSuccessful()){
                    lastProductList = response.body();
                    lastProductAdapter =  new LastProductAdapter(MainActivity.this, lastProductList);
                    recyclerView2.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
                    recyclerView2.setLayoutManager(layoutManager);
                    recyclerView2.setAdapter(lastProductAdapter);
                    lastProductAdapter.notifyDataSetChanged();
                }else{
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<LastProduct>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
}