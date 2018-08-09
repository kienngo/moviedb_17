package com.example.admin.moviebd.data.source.repository;

import android.support.v7.view.menu.MenuView;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.local.MovieLocalDataSource;
import com.example.admin.moviebd.data.source.remote.MovieRemoteDataSource;

import java.util.List;

public class MovieRepository {
    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource, MovieLocalDataSource movieLocalDataSource) {
        this.mMovieRemoteDataSource = movieRemoteDataSource;
        this.mMovieLocalDataSource = movieLocalDataSource;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource movieRemoteDataSource, MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(movieRemoteDataSource, movieLocalDataSource);
        }
        return sInstance;
    }

    public void getMovieFromApi(String url, BaseDataSource.Callback callback) {
        mMovieRemoteDataSource.getMoviesCommon(url, callback);
    }

    public List<Movie> getMoviesLocal(){
        return mMovieLocalDataSource.getListMovies();
    }

    public boolean insertMovie(Movie movie){
        return mMovieLocalDataSource.insertMovieLocal(movie);
    }

    public boolean deleteMovie(Movie movie){
        return mMovieLocalDataSource.deleteMovieLocal(movie);
    }

    public boolean isFavouriteMovie(String movieId){
        return mMovieLocalDataSource.isFavouriteMovie(movieId);
    }
}
