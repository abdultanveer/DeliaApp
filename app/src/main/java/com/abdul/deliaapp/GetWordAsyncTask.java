package com.abdul.deliaapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;

import com.abdul.deliaapp.db.WordDao;
import com.abdul.deliaapp.model.Word;

import java.util.List;

public class GetWordAsyncTask extends AsyncTask<Void, Void, LiveData<List<Word>>> {
    WordDao wordDao;
    ListView listView;
    Context mContext;
    public GetWordAsyncTask(Context context,WordDao wordDao, ListView listView) {
        mContext = context;
        this.wordDao = wordDao;
        this.listView = listView;
    }

    @Override
    protected LiveData<List<Word>> doInBackground(Void... voids) {
       return wordDao.getAllWords();
    }

    @Override
    protected void onPostExecute(LiveData<List<Word>> words) {
        super.onPostExecute(words);
       /* ArrayAdapter<Word> adapter = new ArrayAdapter<Word>(mContext, android.R.layout.simple_list_item_1,
                android.R.id.text1,
                words);
        listView.setAdapter(adapter);*/
    }
}
