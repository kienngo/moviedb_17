package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.remote.MovieInforRemoteDataSource;

public class MovieDetailRepository {
    private static MovieDetailRepository sInstance;
    private MovieInforRemoteDataSource mMovieInforRemoteDataSource;

    private MovieDetailRepository(MovieInforRemoteDataSource movieInforRemoteDataSource) {
        this.mMovieInforRemoteDataSource = movieInforRemoteDataSource;
    }

    public static MovieDetailRepository getInstance(MovieInforRemoteDataSource movieInforRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new MovieDetailRepository(movieInforRemoteDataSource);
        }
        return sInstance;
    }

    public void getMovieInforFromApi(String url, BaseDataSource.Callback callback) {
        mMovieInforRemoteDataSource.getMovieInfor(url, callback);
    }

    public void getTrailerFromApi(String url, BaseDataSource.Callback callback) {
        mMovieInforRemoteDataSource.getTrailer(url, callback);
    }

    public void getRecommendationFromApi(String url, BaseDataSource.Callback callback) {
        mMovieInforRemoteDataSource.getRecommendation(url, callback);
    }

    public void getGenresFromApi(String url, BaseDataSource.Callback callback) {
        mMovieInforRemoteDataSource.getGenres(url, callback);
    }

    public void getCompanyFromApi(String url, BaseDataSource.Callback callback) {
        mMovieInforRemoteDataSource.getCompany(url, callback);
    }
}
