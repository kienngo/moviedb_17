package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.model.SearchResult;
import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.SearchResultDataSource;
import com.example.admin.moviebd.data.source.local.MovieLocalDataSource;

import java.util.List;

public class SearchResultRepository {
    private static SearchResultRepository sInstance;
    private SearchResultDataSource mSearchResultDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private SearchResultRepository(SearchResultDataSource searchResultDataSource,
                                   MovieLocalDataSource movieLocalDataSource) {
        this.mSearchResultDataSource = searchResultDataSource;
        this.mMovieLocalDataSource = movieLocalDataSource;
    }

    public static SearchResultRepository getInstance(SearchResultDataSource searchResultDataSource,
                                                     MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null) {
            sInstance = new SearchResultRepository(searchResultDataSource, movieLocalDataSource);
        }
        return sInstance;
    }

    public void getSearchResultFromApi(String url, BaseDataSource.Callback callback) {
        mSearchResultDataSource.getSearchResultCommon(url, callback);
    }

    public boolean insertMovie(Movie movie) {
        return mMovieLocalDataSource.insertMovieLocal(movie);
    }

    public boolean isFavouriteMovie(String movieId) {
        return mMovieLocalDataSource.isFavouriteMovie(movieId);
    }
}
