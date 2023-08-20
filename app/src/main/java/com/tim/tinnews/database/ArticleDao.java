package com.tim.tinnews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.tim.tinnews.model.Article;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert
    void saveArticle(Article article);

    @Query("Select * From article")
    LiveData<List<Article>> getAllArticles();

    @Delete
    void deleteArticle(Article article);
}
