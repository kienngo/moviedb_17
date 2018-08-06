package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.remote.MovieRemoteDataSource;

public class MovieRepository {
    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource) {
        this.mMovieRemoteDataSource = movieRemoteDataSource;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource movieRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(movieRemoteDataSource);
        }
        return sInstance;
    }

    public void getMovieFromApi(String url, BaseDataSource.Callback callback) {
        mMovieRemoteDataSource.getMoviesCommon(url, callback);
    }
}
