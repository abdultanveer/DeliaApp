package com.abdul.deliaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    EditText nameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //activity_main was inflated
         nameEditText = findViewById(R.id.etName); //getting a handle from the inflated xml
        nameEditText.setOnFocusChangeListener(this);

    }

    public void clickHandler(View view) {
        String name = nameEditText.getText().toString();
        Toast.makeText(this, "welcome to android"+name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(!hasFocus){
            Toast.makeText(this, "this name is already taken", Toast.LENGTH_SHORT).show();
            nameEditText.setText("");
        }

    }
}