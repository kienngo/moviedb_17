package com.example.admin.moviebd.data.source.remote;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.GenresOptionDataSource;
import com.example.admin.moviebd.data.source.GenresResultDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenresRemoteResultDataSouce implements GenresResultDataSource {
    private static GenresRemoteResultDataSouce sInstance;

    private GenresRemoteResultDataSouce() {

    }

    public static GenresRemoteResultDataSouce getInstance() {
        if (sInstance == null) {
            sInstance = new GenresRemoteResultDataSouce();
        }
        return sInstance;
    }

    @Override
    public void getSearchForGenres(String url,
                                   final GenresOptionDataSource.Callback<List<Movie>> callback) {
        new LoadDataAsyntask(new GenresOptionDataSource.Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Movie> movies = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Movie.NameParseUrl.RESULTS);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Movie movie = new Movie(jsonArray.getJSONObject(i));
                        movies.add(movie);
                    }

                    callback.onGetSuccess(movies);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                    throw new RuntimeException(e);
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
