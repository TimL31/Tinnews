package com.tim.tinnews;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.tim.tinnews.database.TinNewsDatabase;
import com.tim.tinnews.model.Article;

public class TinNewsApplication extends Application {

    private static TinNewsDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                TinNewsDatabase.class, "tinnews_db").build();
        // TODO: new code here.
    }

    public static TinNewsDatabase getDatabase() {
        return database;
    }


}
