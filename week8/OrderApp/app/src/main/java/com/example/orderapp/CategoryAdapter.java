package com.example.orderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<Category> arrayCategory;
    public CategoryAdapter(Context context, List<Category> arrayCategory){
        this.context = context;
        this.arrayCategory = arrayCategory;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categories,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = arrayCategory.get(position);
        holder.tensp.setText(category.getName());
        Glide.with(context)
                .load(category.getImages())
                .error(R.drawable.pizza)
                .into(holder.images);
//        Picasso.with(context).load(catego
//        r
//        y.getImages()).into(holder.images);
    }

    @Override
    public int getItemCount() {

        return arrayCategory == null ? 0 : arrayCategory.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tensp;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            images =  itemView.findViewById(R.id.categoryPic);
            tensp = (TextView) itemView.findViewById(R.id.categoryName);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context,"bạn đã chọn category" + tensp.getText().toString(),Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
