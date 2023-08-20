package com.tim.tinnews.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tim.tinnews.databinding.FragmentSearchBinding;
import com.tim.tinnews.repository.NewsRepository;
import com.tim.tinnews.repository.NewsViewModelFactory;

public class SearchFragment extends Fragment {
    private com.tim.tinnews.ui.search.SearchViewModel viewModel;
    private FragmentSearchBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_search, container, false);
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        com.tim.tinnews.ui.search.SearchNewsAdapter newsAdapter = new com.tim.tinnews.ui.search.SearchNewsAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager);
        binding.newsResultsRecyclerView.setAdapter(newsAdapter);

        binding.newsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    viewModel.setSearchInput(query);
                }
                binding.newsSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        NewsRepository newsRepository = new NewsRepository();
//        viewModel = new SearchViewModel(newsRepository);
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(newsRepository))
                .get(com.tim.tinnews.ui.search.SearchViewModel.class);

        viewModel.searchNews().observe(getViewLifecycleOwner(), newsResponse -> {
            newsAdapter.setArticles(newsResponse.articles);
            if (newsResponse !=  null) {
                Log.d("SearchFragment", newsResponse.toString());
            }
        });

        newsAdapter.setItemCallback(article -> {
            SearchFragmentDirections.ActionNavigationSearchToNavigationDetails direction = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article);
            NavHostFragment.findNavController(SearchFragment.this).navigate(direction);
        });
    }
}
