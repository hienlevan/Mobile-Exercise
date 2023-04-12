package com.example.orderapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.orderapp.Model.LastProduct;
import com.example.orderapp.ProductDetailActivity;
import com.example.orderapp.R;

import java.util.List;

public class LastProductAdapter extends RecyclerView.Adapter<LastProductAdapter.MyViewHolder>{
    Context context;
    List<LastProduct> arrayLastProducts;

    public LastProductAdapter(Context context, List<LastProduct> arrayLastProducts){
        this.arrayLastProducts = arrayLastProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LastProduct lastProduct = arrayLastProducts.get(position);
        holder.tensp.setText(lastProduct.getStrMeal());
        holder.id = String.valueOf(lastProduct.getIdMeal());
        Glide.with(context).load(lastProduct.getStrMealThumb()).into(holder.images);
//        Picasso.with(context).load(lastProduct.getStrMealThumb()).into(holder.images);
    }

    @Override
    public int getItemCount() {
        return arrayLastProducts==null?0:arrayLastProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tensp;
        String id;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.popularPic);
            tensp = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                    Toast.makeText(context,"bạn đã chọn last product " + tensp.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
