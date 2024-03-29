package com.tim.tinnews.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tim.tinnews.model.Article;
import com.tim.tinnews.database.ArticleDao;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class TinNewsDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();
}