package com.abdul.deliaapp;

import android.os.AsyncTask;

import com.abdul.deliaapp.db.WordDao;
import com.abdul.deliaapp.model.Word;

public  class insertAsyncTask extends AsyncTask<Word, Void, Void> {

    public WordDao mAsyncTaskDao;

    public insertAsyncTask(WordDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Word... params) {
        mAsyncTaskDao.insert(params[0]);
        return null;
    }
} 