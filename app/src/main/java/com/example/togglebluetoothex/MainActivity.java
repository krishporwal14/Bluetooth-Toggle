package com.example.togglebluetoothex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ToggleButton btToggle;
    BluetoothAdapter ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btToggle = findViewById(R.id.btToggle);
        ba = BluetoothAdapter.getDefaultAdapter();
        btToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (btToggle.isChecked()) {
                    if (ba != null) {
                        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH}, 1);
                        }
                        Boolean ise = ba.enable();
                        if(ise) {
                            Toast.makeText(getApplicationContext(), "Enabled.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    if(ba != null) {
                        Boolean isd = ba.disable();
                        if(isd) {
                            Toast.makeText(getApplicationContext(), "Disabled.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
}