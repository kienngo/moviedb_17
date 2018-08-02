package com.example.admin.moviebd.data.source;


import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface MovieDataSource {
    interface Callback<T> {
        void onStartLoading();

        void onGetSuccess(T data);

        void onGetFailure(Exception exeption);

        void onComplete();
    }

    /*
    urlType : popular or nowplaying or top rated or upcoming
     */
    void getMoviesCommon(String urlType, Callback<List<Movie>> callback);
}
