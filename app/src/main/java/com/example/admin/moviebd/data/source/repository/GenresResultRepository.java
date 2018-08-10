package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.GenresOptionDataSource;
import com.example.admin.moviebd.data.source.GenresResultDataSource;
import com.example.admin.moviebd.data.source.local.MovieLocalDataSource;

public class GenresResultRepository {
    private static GenresResultRepository sInstance;
    private GenresResultDataSource mGenresResultDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private GenresResultRepository(GenresResultDataSource genresResultDataSource,
                                   MovieLocalDataSource movieLocalDataSource) {
        this.mGenresResultDataSource = genresResultDataSource;
        this.mMovieLocalDataSource = movieLocalDataSource;
    }

    public static GenresResultRepository getInstance(GenresResultDataSource genresResultDataSource,
                                                     MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null) {
            sInstance = new GenresResultRepository(genresResultDataSource, movieLocalDataSource);
        }
        return sInstance;
    }

    public void getGenresFromApi(String url, GenresOptionDataSource.Callback callback) {
        mGenresResultDataSource.getSearchForGenres(url, callback);
    }

    public boolean insertMovie(Movie movie){
        return mMovieLocalDataSource.insertMovieLocal(movie);
    }

    public boolean isFavouriteMovie(String movieId){
        return mMovieLocalDataSource.isFavouriteMovie(movieId);
    }
}
