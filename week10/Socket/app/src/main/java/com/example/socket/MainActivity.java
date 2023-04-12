package com.example.socket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button btnPaired;
    ListView listDS;
    public static int REQUEST_BLUETOOTH=1;
    private BluetoothAdapter bluetoothAdapter = null;
    private Set<BluetoothDevice> bluetoothDeviceSet;
    public static String EXTRA_ADDRESS = "device_address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPaired = (Button) findViewById(R.id.button);
        listDS = (ListView) findViewById(R.id.list);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(),"Thiết bị Bluetooth chưa bâth", Toast.LENGTH_SHORT).show();
            finish();
        }else if(!bluetoothAdapter.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Thiết bị Bluetooth chưa bâth", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(),"Thiết bị Bluetooth đã bâth", Toast.LENGTH_SHORT).show();
            startActivityForResult(turnOn,REQUEST_BLUETOOTH);
        }
        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paidredDeviceList();
            }
        });

    }
    public void paidredDeviceList(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
bluetoothDeviceSet = bluetoothAdapter.getBondedDevices();
            ArrayList list = new ArrayList();
            if(bluetoothDeviceSet.size()>0){
                for(BluetoothDevice bt:bluetoothDeviceSet){
                    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getApplicationContext(),"Danh sách thiết bị đã bâth", Toast.LENGTH_SHORT).show();
                        list.add(bt.getName() + "\n" + bt.getAddress());
                    }
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Khong tim thay thiet bị", Toast.LENGTH_SHORT).show();
            }
            final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
            listDS.setAdapter(arrayAdapter);
            listDS.setOnItemClickListener(myListClickListener);
            return;
        }

    }
    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long arg3) {
                String info = ((TextView) view).getText().toString();
                String address = info.substring(info.length() - 17);
                Intent i = new Intent(MainActivity.this,BlueControl.class);
                i.putExtra(EXTRA_ADDRESS,address);
                startActivity(i);
        }
    };
}