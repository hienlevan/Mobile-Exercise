package com.example.myapplication01;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity05 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);

        CheckBox ck1 = (CheckBox) findViewById(R.id.checkBox);
        
        ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                View bg = null;
                if(isChecked){
                    bg.setBackgroundResource(R.drawable.b3);
                }else{
                    bg.setBackgroundResource(R.drawable.b2);
                }
            }
        });

    }
}
