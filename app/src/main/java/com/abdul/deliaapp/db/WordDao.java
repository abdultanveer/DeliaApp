package com.abdul.deliaapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.abdul.deliaapp.model.Word;

import java.util.List;

@Dao
public interface WordDao {

   @Insert
   void insert(Word word);

   @Query("select * from Word")
   public LiveData<List<Word>> getAllWords();

   /*@Update
   public void updateWords(Word... words);*/
}
