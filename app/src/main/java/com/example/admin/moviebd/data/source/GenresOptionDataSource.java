package com.example.admin.moviebd.data.source;

import com.example.admin.moviebd.data.model.Genre;

import java.util.List;

public interface GenresOptionDataSource extends BaseDataSource {
    /*
    url: url get genres
    */
    void getGenres(String url, GenresOptionDataSource.Callback<List<Genre>> callback);
}
