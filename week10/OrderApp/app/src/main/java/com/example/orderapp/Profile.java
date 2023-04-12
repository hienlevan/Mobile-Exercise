package com.example.orderapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.orderapp.Model.User;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    TextView id, username, email, gender;
    Button logoutBtn, btnUpload, btnMain;
    ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnUpload = (Button) findViewById(R.id.button6);
        btnMain = (Button) findViewById(R.id.button7);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, UploadImg.class);
                startActivity(intent);
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            id = findViewById(R.id.textView14);
            username = findViewById(R.id.textView16);
            email = findViewById(R.id.textView20);
            gender = findViewById(R.id.textView22);
            logoutBtn = findViewById(R.id.button3);
            imageView = findViewById(R.id.imageView3);

            User user = SharedPrefManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            username.setText(user.getName());
            gender.setText(user.getGender());
            email.setText(user.getEmail());
            Glide.with(getApplicationContext()).load(user.getImages()).into(imageView);
            logoutBtn.setOnClickListener(this);

        }else {
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.equals(logoutBtn)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}
