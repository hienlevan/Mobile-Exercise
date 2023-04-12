package com.example.socket;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BlueControl  extends AppCompatActivity {
    ImageButton btntb1, btnb2, btndis;
    TextView txt1, txtMac;
    BluetoothAdapter myBluetoothAdapter=null;
    BluetoothSocket btSocket = null;
    private boolean isBTConnected = false;
    Set<BluetoothDevice> pairedDevice1;
    String address = null;
    private ProgressDialog progressDialog;
    int flag1;
    int flag2;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newIntent = getIntent();
        address = newIntent.getStringExtra(MainActivity.EXTRA_ADDRESS);
        setContentView(R.layout.activity_control);
        btntb1 = (ImageButton) findViewById(R.id.btn1);
        btnb2  = (ImageButton) findViewById(R.id.btn2);
        txt1 = (TextView) findViewById(R.id.textV1);
        txtMac = (TextView) findViewById(R.id.textViewMAC);
        btndis = (ImageButton) findViewById(R.id.btnDisc);
        new ConnectBT().execute();
        btntb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietbi1();
            }
        });
        btnb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietbi2();
            }
        });
//        btndis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dis();
//            }
//        });
    }
    private void thietbi1(){
        if(btSocket!=null){
            try {
                if(this.flag1 ==0){
                    this.flag1=1;
                    this.btntb1.setBackgroundResource(R.drawable.ry);
                    btSocket.getOutputStream().write("1".toString().getBytes());
                    txt1.setText("Thiết bị 1 đang bật");
                    return;
                }else{
                    if(this.flag1 != 1) return;
                    {
                        this.flag1 = 0;
                        this.btntb1.setBackgroundResource(R.drawable.ry);
                        btSocket.getOutputStream().write("A".toString().getBytes());
                        txt1.setText("Thiết bị 1 đang tắt");
                        return;
                    }
                }
            }catch (IOException e){
                msg("Lỗi");
            }
        }
    }
    private void thietbi2(){
        if(btSocket!=null){
            try {
                if(this.flag2 ==0){
                    this.flag2=1;
                    this.btnb2.setBackgroundResource(R.drawable.ry);
                    btSocket.getOutputStream().write("2".toString().getBytes());
                    txt1.setText("Thiết bị 2 đang bật");
                    return;
                }else{
                    if(this.flag2 != 1) return;
                    {
                        this.flag2 = 0;
                        this.btnb2.setBackgroundResource(R.drawable.ry);
                        btSocket.getOutputStream().write("B".toString().getBytes());
                        txt1.setText("Thiết bị 2 đang tắt");
                        return;
                    }
                }
            }catch (IOException e){
                msg("Lỗi");
            }
        }
    }
    private class ConnectBT extends AsyncTask<Void, Void, Void>{
        private boolean ConnnectSuccess = true;
        @Override
        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(BlueControl.this, "đang kết nỗi", "XIn vui lòng đợi");

        }
        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if(btSocket==null||!isBTConnected){
                    myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice bluetoothDevice = myBluetoothAdapter.getRemoteDevice(address);
                    if(ActivityCompat.checkSelfPermission(BlueControl.this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
                        btSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        btSocket.connect();
                    }
                }
            }catch (IOException e){
                ConnnectSuccess = false;
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            if(!ConnnectSuccess){
                msg("Kết nối thất bại");
                finish();
            }
            else{
                msg("Kết nối thành côngn");
                isBTConnected = true;
                pairedDevice1List();
            }
        }
    }
    private void pairedDevice1List(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
            pairedDevice1 = myBluetoothAdapter.getBondedDevices();
            if(pairedDevice1.size()>0){
                for(BluetoothDevice bt:pairedDevice1){
                    txtMac.setText(bt.getName() + " - " + bt.getAddress());
                }
            }else{
                Toast.makeText(getApplicationContext(),"không tìm thấy kết nối", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void msg(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
