package com.hcmute.tuannm.loginandregisterretrofit2api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    TextView txtSignUp;
    EditText edtPassword, edtEmail;
    APIService apiService;
    ImageView imgLogin;
    Account accountLogin;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();
                Intent intent = getIntent();
                accountLogin = (Account) intent.getSerializableExtra("account");
                if(email.equals(accountLogin.getEmail()) && password.equals(accountLogin.getPassword())) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        txtSignUp = (TextView) findViewById(R.id.textViewSignUp);
        edtEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        edtPassword = (EditText) findViewById(R.id.editTextPasswordLogin);
        imgLogin = (ImageView) findViewById(R.id.imageViewLogin);
    }
}
