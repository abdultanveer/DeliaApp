package com.abdul.deliaapp;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    String[] languages = new String[]{"english","chinese","urdu"};
    EditText nameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //activity_main was inflated
        nameEditText = findViewById(R.id.etName); //getting a handle from the inflated xml

        ListView languagesListView = findViewById(R.id.lv_languages);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,languages);
        languagesListView.setAdapter(adapter);


//        nameEditText.setOnFocusChangeListener(this);

    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.buttonLogin:
                TextView loginTextView = findViewById(R.id.tvLoginMessage);
                loginTextView.setText(nameEditText.getText().toString());
               /* String name = nameEditText.getText().toString();
                createAlarm("demo alarm",9,12);
                Toast.makeText(this, "alarm set--"+name, Toast.LENGTH_SHORT).show();*/

                break;
            case R.id.buttonCancel:
                Intent waterIntent = new Intent();
                waterIntent.setAction("ineed.water"); //implicit intent
                startActivity(waterIntent);
                break;
            case R.id.buttonStart:
                Intent safrIntent = new Intent(MainActivity.this,SecondActivity.class);//explicit
                startActivityForResult(safrIntent,123);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 123){
            String result = data.getExtras().getString("res");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
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