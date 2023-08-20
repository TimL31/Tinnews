package com.tim.tinnews.ui.home;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.tim.tinnews.model.Article;
import com.tim.tinnews.model.NewsResponse;
import com.tim.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }


    public LiveData<NewsResponse> getTopHeadlines() {
        // country change -> repository.getTopHeadlines(country)
        // string -> LiveData<NewsResponse>

//        return Transformations.switchMap(countryInput, new Function<String, LiveData<NewsResponse>>() {
//            @Override
//            public LiveData<NewsResponse> apply(String input) {
//                return repository.getTopHeadlines("us");
//            }
//        });

        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }

    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }
}
