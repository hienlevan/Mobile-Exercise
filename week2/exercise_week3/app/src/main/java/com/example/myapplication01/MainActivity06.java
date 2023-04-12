package com.example.myapplication01;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity06 extends AppCompatActivity {
    private View bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_06);



        
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkID trả về ID radio
                switch (checkedId){
                    case R.id.radioButton:
                        bg.setBackgroundResource(R.drawable.b1);
                        break;
                        case R.id.radioButton2:
                            bg.setBackgroundResource(R.drawable.b2);
                            break;
                }
            }
        });
    }
}