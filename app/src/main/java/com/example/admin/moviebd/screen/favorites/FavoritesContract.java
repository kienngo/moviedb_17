package com.example.admin.moviebd.screen.favorites;

import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface FavoritesContract {
    interface View{
        void showMovieFromLocal(List<Movie> movies);
    }

    interface Presenter{
        void getMovieFromLocal();
        boolean deleteMovie(Movie movie);
    }
}
