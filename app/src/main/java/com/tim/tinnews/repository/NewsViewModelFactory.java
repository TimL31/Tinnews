package com.tim.tinnews.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tim.tinnews.ui.home.HomeViewModel;
import com.tim.tinnews.ui.save.SaveViewModel;
import com.tim.tinnews.ui.search.SearchViewModel;
import com.tim.tinnews.repository.NewsRepository;

public class NewsViewModelFactory implements ViewModelProvider.Factory{

    private final NewsRepository repository;

    public NewsViewModelFactory(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        } else if (modelClass.isAssignableFrom(SaveViewModel .class)) {
            return (T) new SaveViewModel(repository);
        } else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }
}
