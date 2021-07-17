package com.abdul.deliaapp.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.abdul.deliaapp.insertAsyncTask;
import com.abdul.deliaapp.model.Word;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDB db = WordRoomDB.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // Must insert data off the main thread
    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }
}


