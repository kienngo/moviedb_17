package com.example.admin.moviebd.data.source.remote;

import com.example.admin.moviebd.data.model.SearchResult;
import com.example.admin.moviebd.data.source.SearchResultDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultRemoteDataSource implements SearchResultDataSource {
    private static SearchResultRemoteDataSource sInstance;

    private SearchResultRemoteDataSource() {

    }

    public static SearchResultRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SearchResultRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getSearchResultCommon(String url, final Callback<List<SearchResult>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<SearchResult> searchResults = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(SearchResult.NameParseUrl.RESULTS);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        SearchResult searchResult = new SearchResult(jsonArray.getJSONObject(i));
                        searchResults.add(searchResult);
                    }

                    callback.onGetSuccess(searchResults);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }

            @Override
            public void onGetFailure(Exception exception) {
                callback.onGetFailure(exception);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(url);
    }
}
