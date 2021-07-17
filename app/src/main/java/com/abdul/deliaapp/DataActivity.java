package com.abdul.deliaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class DataActivity extends AppCompatActivity {
    public static final String FILE_NAME = "deliasp";
    EditText nameEditText;
    CheckBox rempwdCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        nameEditText = findViewById(R.id.etSpname);
        rempwdCheckBox = findViewById(R.id.checkBoxRemPwd);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    private void restoreData() {
        //read from the file
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        //set the data into et n cb
        String name = sharedPreferences.getString("name","");
        boolean isChecked = sharedPreferences.getBoolean("rp",false);
        nameEditText.setText(name);
        rempwdCheckBox.setChecked(isChecked);

    }

    private void saveData() {
        //get data from edittext and cb
       String name = nameEditText.getText().toString();
       boolean isChecked = rempwdCheckBox.isChecked();
        //create file
       SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        //open the file
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //write to the file
        editor.putBoolean("rp",isChecked);
        editor.putString("name",name);
        //save the file
        editor.apply();
    }
}