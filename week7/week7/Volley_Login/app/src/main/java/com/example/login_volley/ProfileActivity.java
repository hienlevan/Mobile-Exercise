package com.example.login_volley;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            id = findViewById(R.id.rowId);
            userName = findViewById(R.id.rowUsername);
            userEmail = findViewById(R.id.rowEmail);
            gender = findViewById(R.id.rowGender);
            btnLogout = findViewById(R.id.logoutBtn);
            imageViewprofile = findViewById(R.id.imgUser);
            User user = SharedPrefManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            userName.setText(String.valueOf(user.getName()));
            gender.setText(String.valueOf(user.getGender()));
            userEmail.setText(String.valueOf(user.getEmail()));
            Glide.with(getApplicationContext())
                    .load(user.getImages())
                    .into(imageViewprofile);
            btnLogout.setOnClickListener(this);
        }else{
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}