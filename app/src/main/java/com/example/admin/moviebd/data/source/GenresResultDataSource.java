package com.example.admin.moviebd.data.source;

import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface GenresResultDataSource extends BaseDataSource{
    /*
    url: url get result genres
    */
    void getSearchForGenres(String url, GenresResultDataSource.Callback<List<Movie>> callback);
}
