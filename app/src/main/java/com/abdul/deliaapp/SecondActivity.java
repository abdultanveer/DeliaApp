package com.abdul.deliaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void handleClicks(View view) {
        EditText etResult = findViewById(R.id.etResult);
        String result = etResult.getText().toString();
        Intent resIntent = new Intent();
        resIntent.putExtra("res",result);
        setResult(RESULT_OK,resIntent);
        finish();
    }
}