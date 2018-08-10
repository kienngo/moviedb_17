package com.example.admin.moviebd.screen.search.result;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.model.SearchResult;

import java.util.List;

public interface ResultSearchContact {
    interface View{
        void onStartLoading();
        void onSuccess(List<SearchResult> searchResults);
        void onFailed(Exception exception);
        void onDismissLoading();
    }

    interface Presenter{
        void getDataSearchFromUrl(String url);
        boolean insertMovieLocal(Movie movie);
        boolean isFavoritesLocal(String movieId);
    }
}
