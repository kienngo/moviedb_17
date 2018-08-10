package com.example.admin.moviebd.screen.genres.result;

import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface ResultGenresContract {
    interface View {
        void onStartLoading();

        void onSuccess(List<Movie> movies);

        void onFailed(Exception exception);

        void onDismissLoading();
    }

    interface Presenter {
        void getGenresFromApi(String url);
        boolean insertMovieLocal(Movie movie);
        boolean isFavoritesLocal(String movieId);
    }
}
