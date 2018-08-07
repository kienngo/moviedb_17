package com.example.admin.moviebd.data.source.remote;

import com.example.admin.moviebd.data.model.Genre;
import com.example.admin.moviebd.data.source.GenresOptionDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenresRemoteOptionDataSource implements GenresOptionDataSource {
    private static GenresRemoteOptionDataSource sInstance;

    private GenresRemoteOptionDataSource() {

    }

    public static GenresRemoteOptionDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new GenresRemoteOptionDataSource();
        }
        return sInstance;
    }

    @Override
    public void getGenres(String url, final GenresOptionDataSource.Callback<List<Genre>> callback) {
        new LoadDataAsyntask(new GenresOptionDataSource.Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Genre> genres = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Genre.NameParseUrl.GENRES);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Genre dataGenre = new Genre(jsonArray.getJSONObject(i));
                        genres.add(dataGenre);
                    }

                    callback.onGetSuccess(genres);
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
