package com.abdul.deliaapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    @PrimaryKey(autoGenerate=true)
    private int uid;

    //@ColumnInfo(name = "word_col")
    String word;


    public Word(String word) {
        this.word = word;

    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "\n"+word;
    }
}
