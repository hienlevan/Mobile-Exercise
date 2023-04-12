package com.example.myapplication01;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity02 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);

        bg.setBackgroundColor(Color.BLUE);

        bg.setBackgroundResource(R.drawable.kotlinbrg);

        Random background;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.kotlinbrg);
        arrayList.add(R.drawable.b1);
        arrayList.add(R.drawable.b2);
        arrayList.add(R.drawable.b3);
        Random random = new Random();
        int vitri = random.nextInt(arrayList.size());
        bg.setBackgroundResource(arrayList.get(vitri));
    }
}
