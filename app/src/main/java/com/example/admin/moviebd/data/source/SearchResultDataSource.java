package com.example.admin.moviebd.data.source;

import com.example.admin.moviebd.data.model.SearchResult;

import java.util.List;

public interface SearchResultDataSource extends BaseDataSource {
    /*
    url : url search multi
     */
    void getSearchResultCommon(String url, SearchResultDataSource.Callback<List<SearchResult>> callback);
}
