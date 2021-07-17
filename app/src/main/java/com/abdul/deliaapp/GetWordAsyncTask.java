package com.abdul.deliaapp;

import android.os.AsyncTask;
import android.widget.TextView;

import com.abdul.deliaapp.db.WordDao;
import com.abdul.deliaapp.model.Word;

import java.util.List;

public class GetWordAsyncTask extends AsyncTask<Void,Void,List<Word>> {
    WordDao wordDao;
    TextView mTextView;
    public GetWordAsyncTask(WordDao wordDao, TextView textView) {
        this.wordDao = wordDao;
        mTextView = textView;
    }

    @Override
    protected List<Word> doInBackground(Void... voids) {
       return wordDao.getAllWords();
    }

    @Override
    protected void onPostExecute(List<Word> words) {
        super.onPostExecute(words);
        mTextView.setText(words.toString());
    }
}
