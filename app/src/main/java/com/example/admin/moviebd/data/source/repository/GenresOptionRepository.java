package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.source.GenresOptionDataSource;

public class GenresOptionRepository {
    private static GenresOptionRepository sInstance;
    private GenresOptionDataSource mGenresOptionDataSource;

    private GenresOptionRepository(GenresOptionDataSource genresOptionDataSource) {
        this.mGenresOptionDataSource = genresOptionDataSource;
    }

    public static GenresOptionRepository getInstance(GenresOptionDataSource genresOptionDataSource) {
        if (sInstance == null) {
            sInstance = new GenresOptionRepository(genresOptionDataSource);
        }
        return sInstance;
    }

    public void getGenresFromApi(String url, GenresOptionDataSource.Callback callback) {
        mGenresOptionDataSource.getGenres(url, callback);
    }
}
