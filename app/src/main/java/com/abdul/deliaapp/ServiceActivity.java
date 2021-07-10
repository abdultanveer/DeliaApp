package com.abdul.deliaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void handleService(View view) {
        Intent serviceIntent = new Intent(ServiceActivity.this,MyService.class);

        switch (view.getId()){
            case R.id.buttonStart:
                startService(serviceIntent);
                break;
            case R.id.buttonStop:
                stopService(serviceIntent);
                break;
        }
    }
}