package com.example.admin.moviebd.screen.movies;

import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface MoviesContract {
    interface View{
        void onStartLoading();
        void onSuccess(List<Movie> movies);
        void onFailed(Exception exception);
        void onDismissLoading();
    }

    interface Presenter{
        void getMovieLoadMore(String url);
        boolean insertMovieLocal(Movie movie);
        boolean isFavoritesLocal(String movieId);
    }
}
