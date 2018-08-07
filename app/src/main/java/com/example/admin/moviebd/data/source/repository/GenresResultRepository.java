package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.source.GenresOptionDataSource;
import com.example.admin.moviebd.data.source.GenresResultDataSource;

public class GenresResultRepository {
    private static GenresResultRepository sInstance;
    private GenresResultDataSource mGenresResultDataSource;

    private GenresResultRepository(GenresResultDataSource genresResultDataSource) {
        this.mGenresResultDataSource = genresResultDataSource;
    }

    public static GenresResultRepository getInstance(GenresResultDataSource genresResultDataSource) {
        if (sInstance == null) {
            sInstance = new GenresResultRepository(genresResultDataSource);
        }
        return sInstance;
    }

    public void getGenresFromApi(String url, GenresOptionDataSource.Callback callback) {
        mGenresResultDataSource.getSearchForGenres(url, callback);
    }
}
