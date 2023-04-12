package com.example.orderapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.orderapp.Model.ProductDetail;
import com.example.orderapp.Model.ProductDetailTrue;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    TextView headingProductDetail, titlePrice, quantity, desc;
    String id;
    ImageView imageProduct;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        AnhXa();
        showDetail();
    }
    private void AnhXa(){
        headingProductDetail = (TextView) findViewById(R.id.headingDetail);
        titlePrice = (TextView) findViewById(R.id.titlePrice);
        quantity = (TextView) findViewById(R.id.quantityNumber);
        desc = (TextView) findViewById(R.id.descriptionView);
        imageProduct = (ImageView) findViewById(R.id.imageProduct);
//        id = (TextView) findViewById(R.id.idProduct);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!= null){
            String string = bundle.getString("tên","Không có");
            id = bundle.getString("id","khong có id");
//            heading.setText(string + "" + id);
        }
    }
    private void showDetail(){
        apiService = RetrofitClient.getInstance();
        RequestBody requestBodyProduct = RequestBody.create(MediaType.parse("form-data"),id);
        apiService.getMealDetail(requestBodyProduct).enqueue(new Callback<ProductDetailTrue>() {
            @Override
            public void onResponse(Call<ProductDetailTrue> call, Response<ProductDetailTrue> response) {
                ProductDetailTrue productDetailTrue = response.body();
                List<ProductDetail> arrayProduct =  productDetailTrue.getResult();

                    headingProductDetail.setText(arrayProduct.get(0).getMeal());
                    titlePrice.setText(String.valueOf(arrayProduct.get(0).getPrice()));
                    desc.setText(arrayProduct.get(0).getInstructions());
                Glide.with(ProductDetailActivity.this).load(arrayProduct.get(0).getStrmealthumb()).into(imageProduct);

            }

            @Override
            public void onFailure(Call<ProductDetailTrue> call, Throwable t) {
                Log.e("Tag", t.toString());
                Toast.makeText(ProductDetailActivity.this, "Gọi API thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
