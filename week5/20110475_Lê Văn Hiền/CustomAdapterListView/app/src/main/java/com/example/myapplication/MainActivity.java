package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
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
        listView.setAdapter(adapter);
    }

    private void AnhXa(){
        listView = (ListView) findViewById(R.id.listview1);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.img));
        arrayList.add(new MonHoc("C#","C# 1",R.drawable.img_7));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.img_2));
        arrayList.add(new MonHoc("Kotlin","Kotlin1",R.drawable.img_3));
        arrayList.add(new MonHoc("Dart","Dart 1",R.drawable.img_4));
    }
}
