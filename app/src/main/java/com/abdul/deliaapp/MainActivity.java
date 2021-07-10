package com.abdul.deliaapp;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setContentView(R.layout.activity_main); //activity_main was inflated --layout inflater
        nameEditText = findViewById(R.id.etName); //getting a handle from the inflated xml

        ListView languagesListView = findViewById(R.id.lv_languages);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,languages);
        languagesListView.setAdapter(adapter);

       /* ConstraintLayout constraintLayout = findViewById(R.id.clayout);
        TextView myTextView = new TextView(this);
        myTextView.setText("dynamically created textview");
        constraintLayout.addView(myTextView);*/


//        nameEditText.setOnFocusChangeListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()){
             case R.id.fMenuItem:
                 Toast.makeText(this, "first item", Toast.LENGTH_SHORT).show();
                 break;
             case R.id.sMenuItem:
                 Toast.makeText(this, "second item", Toast.LENGTH_SHORT).show();

                 break;
         }

        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.buttonLogin:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("welcome to alert dialog")
                        .setTitle("demo");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                /*TextView loginTextView = findViewById(R.id.tvLoginMessage);
                loginTextView.setText(nameEditText.getText().toString());*/
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
                safrIntent.putExtra("mykey",nameEditText.getText().toString());
                startActivityForResult(safrIntent,123);
                break;
            case R.id.buttonDial:
                Intent dialIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:12345678" ));
                        //"http://www.google.com"));
                        //new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345678"));
                startActivity(dialIntent);
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