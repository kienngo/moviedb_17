package com.example.admin.moviebd.data.source.local.sqlite;

import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface MoviesDao {
    List<Movie> getMovies();

    Movie getMovie(long movieId);

    boolean insertMovie(Movie movie);

    boolean deleteMovie(Movie movie);

    boolean isFavouriteMovie(String movieId);
}
