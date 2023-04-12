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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.orderapp.ItemShow;
import com.example.orderapp.Model.Category;
import com.example.orderapp.R;

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
        holder.id.setText(String.valueOf(category.getId()));

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
        public TextView tensp, id;
        public ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView){

            super(itemView);
            images =  itemView.findViewById(R.id.categoryPic);
            id = itemView.findViewById(R.id.textView23);
            tensp = (TextView) itemView.findViewById(R.id.categoryName);
            layout = (ConstraintLayout) itemView.findViewById(R.id.mainLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ItemShow.class);
                    intent.putExtra("id",id.getText().toString());
                    intent.putExtra("tên", tensp.getText().toString());
                    context.startActivity(intent);
                    Toast.makeText(context,"bạn đã chọn category" + tensp.getText(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
