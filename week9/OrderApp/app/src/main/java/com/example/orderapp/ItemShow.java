package com.example.orderapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemShow extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemShowAdapter itemShowAdapter;
    APIService apiService;
    TextView heading;
    String id;
//     ProgressDialog mProgressDialog;
    public static final int MY_REQUEST_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        AnhXa();
        UploadImg1();
    }
    private void AnhXa(){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView3);
        heading = (TextView) findViewById(R.id.textView18);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!= null){
            String string = bundle.getString("tên","Không có");
            id = bundle.getString("id","khong có id");
            heading.setText(string + "" + id);
        }
    }
    public void UploadImg1(){
//        mProgressDialog.show();
        apiService = RetrofitClient.getInstance();
        RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("form-data"),id);
        apiService.getCategoryItem(requestBodyUsername).enqueue(new Callback<List<LastProduct>>() {
            @Override
            public void onResponse(Call<List<LastProduct>> call, Response<List<LastProduct>> response) {
//                mProgressDialog.dismiss();
                List<LastProduct> lastProducts = response.body();
                if(lastProducts.size() > 0){
                    itemShowAdapter =  new ItemShowAdapter(ItemShow.this, lastProducts);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(itemShowAdapter);
                    itemShowAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(ItemShow.this,"Thất bại",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LastProduct>> call, Throwable t) {
//                mProgressDialog.dismiss();
                Log.e("TAG", t.toString());
                Toast.makeText(ItemShow.this,"Gọi API thất bại",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
