package com.example.sharedppreferrences2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener(){
        @Override
        public boolean onEditorAction (TextView textView,int id, KeyEvent){
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                attemptLogin();
            }
        });

        CheckBoxRememberMe = (CheckBox)findViewById(R.id.checkBoxRememberMe);
        if(!new PreManager(this).isUserLogedOuut()){
            startHomeActivity();
        }
    }

    private void attemptLogin(){
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView .getText().toString();
        boolean cancel = false;
        View focusView = null;
        if(!TextUtils.isEmpty(password) && !isPassrdValid(password)){
            mPasswordView. setError (getString(R.string.err_invalid_password));
            focusView = mPasswordView;
            cancel true;
        }

    }
}