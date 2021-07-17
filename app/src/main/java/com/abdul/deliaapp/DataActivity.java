package com.abdul.deliaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.abdul.deliaapp.db.WordDao;
import com.abdul.deliaapp.db.WordRoomDB;
import com.abdul.deliaapp.db.WordViewModel;
import com.abdul.deliaapp.model.Word;

public class DataActivity extends AppCompatActivity {
    public static final String FILE_NAME = "deliasp";
    EditText nameEditText;
    CheckBox rempwdCheckBox;
    WordDao wordDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        nameEditText = findViewById(R.id.etSpname);
        rempwdCheckBox = findViewById(R.id.checkBoxRemPwd);
        WordRoomDB  wordRoomDB = WordRoomDB.getDatabase(this);
        wordDao = wordRoomDB.wordDao();
        TextView mNameTextView = findViewById(R.id.tvLiveData);
        WordViewModel mModel = ViewModelProviders.of(this).get(WordViewModel.class);





        mModel.getAllWords().observe(this,words1 -> {
            if(words1.size()!=0) {
                int size = words1.size();
                Word word = words1.get(size - 1);
                mNameTextView.setText(word.getWord());
            }
        });

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

    public void dbHandler(View view) {
        switch (view.getId()){
            case R.id.btnCommit:
                insertWord();

                break;
            case R.id.btnQuery:
                getData();
                break;
        }


    }

    private void getData() {
        //TextView textView = findViewById(R.id.tvDb);
        ListView dbListView = findViewById(R.id.dblist);
        GetWordAsyncTask getWordAsyncTask = new GetWordAsyncTask(this,wordDao,dbListView);
        getWordAsyncTask.execute();
    }

    private void insertWord() {
        insertAsyncTask insertAsyncTask = new insertAsyncTask(wordDao);
        insertAsyncTask.execute(new Word(nameEditText.getText().toString()));
        //wordDao.insert();
    }
}