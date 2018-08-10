package com.example.admin.moviebd.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.MovieDataSource;
import com.example.admin.moviebd.data.source.local.sqlite.DbHelper;
import com.example.admin.moviebd.data.source.local.sqlite.MoviesDao;
import com.example.admin.moviebd.data.source.local.sqlite.MoviesDaoImpl;
import com.example.admin.moviebd.data.source.repository.MovieRepository;

import java.util.List;

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource{
    private static MovieLocalDataSource sInstance;
    private MoviesDao mMoviesDao;

    private MovieLocalDataSource(MoviesDao moviesDao) {
        mMoviesDao = moviesDao;
    }

    public static MovieLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new MovieLocalDataSource(
                    MoviesDaoImpl.getInstance(DbHelper.getInstance(context)));
        }
        return sInstance;
    }

    @Override
    public List<Movie> getListMovies() {
        return mMoviesDao.getMovies();
    }

    @Override
    public boolean insertMovieLocal(Movie movie) {
        return mMoviesDao.insertMovie(movie);
    }

    @Override
    public boolean deleteMovieLocal(Movie movie) {
        return mMoviesDao.deleteMovie(movie);
    }

    @Override
    public boolean isFavouriteMovie(String movieId) {
        return mMoviesDao.isFavouriteMovie(movieId);
    }
}
