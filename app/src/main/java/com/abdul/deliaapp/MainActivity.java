package com.abdul.deliaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
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
//        nameEditText.setOnFocusChangeListener(this);

    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.buttonLogin:
                String name = nameEditText.getText().toString();
                createAlarm("demo alarm",9,12);
                Toast.makeText(this, "alarm set--"+name, Toast.LENGTH_SHORT).show();

                break;
            case R.id.buttonCancel:
                Intent waterIntent = new Intent();
                waterIntent.setAction("ineed.water");
                startActivity(waterIntent);
                break;
        }

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(!hasFocus){
            Toast.makeText(this, "this name is already taken", Toast.LENGTH_SHORT).show();
            nameEditText.setText("");
        }

    }

    public void createAlarm(String message, int hour, int minutes) {

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}