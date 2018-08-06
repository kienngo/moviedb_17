package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.SearchResultDataSource;

public class SearchResultRepository {
    private static SearchResultRepository sInstance;
    private SearchResultDataSource mSearchResultDataSource;

    private SearchResultRepository(SearchResultDataSource searchResultDataSource) {
        this.mSearchResultDataSource = searchResultDataSource;
    }

    public static SearchResultRepository getInstance(SearchResultDataSource searchResultDataSource) {
        if (sInstance == null) {
            sInstance = new SearchResultRepository(searchResultDataSource);
        }
        return sInstance;
    }

    public void getSearchResultFromApi(String url, BaseDataSource.Callback callback) {
        mSearchResultDataSource.getSearchResultCommon(url, callback);
    }
}
