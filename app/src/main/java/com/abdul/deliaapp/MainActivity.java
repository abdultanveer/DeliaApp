package com.abdul.deliaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //activity_main was inflated
    }

    public void clickHandler(View view) {
        EditText nameEditText = findViewById(R.id.etName); //getting a handle from the inflated xml
        String name = nameEditText.getText().toString();
        Toast.makeText(this, "welcome to android"+name, Toast.LENGTH_SHORT).show();
    }
}