package com.example.myapplication01;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity03 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);

        ImageButton img2 = (ImageButton) findViewById(R.id.imageButton1);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSwitcher img1 = null;
                img1.setImageResource(R.drawable.b1);
                img1.getLayoutParams().width = 550;
                img1.getLayoutParams().height = 550;
            }
        });
    }
}