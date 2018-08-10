package com.example.admin.moviebd.screen.movie;

import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface MovieContract {
    interface View{
        void onStartLoading();
        void onSuccess(List<Movie> movies, int typeMovie);
        void onFailed(Exception exception, int typeMovieFail);
        void onDismissLoading();
    }

    interface Presenter{
        void getMoviePopularFromApi(String url);
        void getMovieNowplayingFromApi(String url);
        void getMovieTopRatedFromApi(String url);
        void getMovieUpcomingFromApi(String url);
        boolean insertMovieLocal(Movie movie);
        boolean isFavoritesLocal(String movieId);
    }
}
