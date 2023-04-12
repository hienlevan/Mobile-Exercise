package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<MonHoc> arrayList;
    MonhocAdapter adapter;
    EditText editText1;
    Button btnNhap, btnCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        //Tạo Adapter
        adapter = new MonhocAdapter(MainActivity.this, R.layout.row_monhoc, arrayList);
        //truyền dữ liệu từ adapter ra listview
        gridView.setAdapter(adapter);
    }

    private void AnhXa(){
         gridView = (GridView) findViewById(R.id.grid_view);

        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.img));
        arrayList.add(new MonHoc("C#","C# 1",R.drawable.img_1));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.img_3));
        arrayList.add(new MonHoc("Kotlin","Kotlin1",R.drawable.img_3));
        arrayList.add(new MonHoc("Dart","Dart 1",R.drawable.img_4));
    }
}