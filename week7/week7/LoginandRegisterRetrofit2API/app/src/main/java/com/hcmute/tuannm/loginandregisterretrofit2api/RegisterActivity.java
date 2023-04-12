package com.hcmute.tuannm.loginandregisterretrofit2api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class RegisterActivity extends AppCompatActivity {

    EditText edtPassword, edtEmail, edtName;
    APIService apiService;
    Account accountRegister;
    ImageView imgRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AnhXa();

        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String password = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();

                accountRegister =  new Account(name,email,password);
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.putExtra("account",accountRegister);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        edtName = (EditText) findViewById(R.id.editTextName);
        imgRegister = (ImageView) findViewById(R.id.imageViewRegister);
    }
}
