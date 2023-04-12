package com.example.orderapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.orderapp.Model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImg extends AppCompatActivity {
    Timer timer;
    TextView textViewUserName;
    EditText editTextUserName;
    Button btnChoose, btnUpload;
    ImageView imageViewUpload, imageViewChoose;
    private Uri mUri;
    private ProgressDialog mProgressDialog;
    public static final int MY_REQUEST_CODE=100;
    public static final String TAG = UploadImg.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        timer = new Timer();
        AnhXa();
        mProgressDialog = new ProgressDialog(UploadImg.this);
        mProgressDialog.setMessage("Please wait upload...");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermissions();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri != null){
                    UploadImg1();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(UploadImg.this, Profile.class);
                            startActivity(intent);
                            finish();
                        }
                    },5000);

                }
            }
        });
    }
    private void AnhXa(){
        btnChoose = findViewById(R.id.button4);
        btnUpload = findViewById(R.id.button5);
        imageViewChoose = findViewById(R.id.imageView4);
        imageViewUpload = findViewById(R.id.imageView6);
        textViewUserName = findViewById(R.id.textView17);
        editTextUserName = findViewById(R.id.editTextTextPersonName2);
    }
    public static String[] storage_permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] storage_permissions_33 = {
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
    };
    public static String[] permissions() {
        String[] p;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            p = storage_permissions_33;
        }
        else {
            p = storage_permissions;
        }
        return p;
    }
    public void CheckPermissions(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else{
            requestPermissions(permissions(),MY_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_DENIED){
                openGallery();
            }
        }
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mIntentActivityResultLauncher.launch(Intent.createChooser(intent,"select Picture"));
    }
    private ActivityResultLauncher<Intent> mIntentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data==null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imageViewChoose.setImageBitmap(bitmap);
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    public void UploadImg1(){
        mProgressDialog.show();
        int id = SharedPrefManager.getInstance(this).getUser().getId();
        RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(id));
        String IMAGE_PATH = RealPathUtil.getRealPath(this,mUri);
        Log.e("ffff", IMAGE_PATH);
        File file = new File(IMAGE_PATH);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part partbodyavatar = MultipartBody.Part.createFormData(Const.MY_IMAGES,file.getName(),requestFile);
        APIService.API_SERVICE.updateimages(requestBodyUsername,partbodyavatar).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mProgressDialog.dismiss();
                List<User> imageUploads = response.body();
                if(imageUploads.size() > 0){
                    for (int i= 0 ; i<imageUploads.size();i++){
                        textViewUserName.setText(imageUploads.get(i).getId());
                        Glide.with(UploadImg.this).load(imageUploads.get(i).getImages()).into(imageViewUpload);
                        Toast.makeText(UploadImg.this,"thành công",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(UploadImg.this,"Thất bại",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mProgressDialog.dismiss();
                Log.e("TAG", t.toString());
                Toast.makeText(UploadImg.this,"Gọi API thất bại",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
