package com.example.admin.moviebd.data.source.remote;


import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.MovieDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {
    private static MovieRemoteDataSource sInstance;

    private MovieRemoteDataSource() {

    }

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getMoviesCommon(String url, final BaseDataSource.Callback<List<Movie>> callback) {
        new LoadDataAsyntask(new BaseDataSource.Callback() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                List<Movie> movies = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject((String) data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Movie.NameParseUrl.RESULTS);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Movie movie = new Movie(jsonArray.getJSONObject(i));
                        movies.add(movie);
                    }

                    callback.onGetSuccess(movies);
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
