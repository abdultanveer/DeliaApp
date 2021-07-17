package com.abdul.deliaapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abdul.deliaapp.model.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDB extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDB INSTANCE;

    public static WordRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDB.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
