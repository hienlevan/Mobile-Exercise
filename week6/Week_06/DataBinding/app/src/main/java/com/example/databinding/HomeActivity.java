package com.example.databinding;

import android.database.Observable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databinding.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;
    private ActivityHomeBinding binding;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        title.set("ví dụ về DataBinding cho RecycleView");
        binding.setHome(this);
        setData();
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setAdapter(listUserAdapter);


    }

    private void setData(){
        List<User> userList = new ArrayList<>();
        int numUsers = 10;
        for(int i= 0; i< numUsers; i++){
            User user = new User();
            user.setFirstName("Hữu" + i);
            user.setLastName("Trung" + i);
            userList.add(user);
        }
        listUserAdapter = new ListUserAdapter(userList);
    }




}
