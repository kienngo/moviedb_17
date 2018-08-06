package com.example.admin.moviebd.data.source;


import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface MovieDataSource extends BaseDataSource {
    /*
    urlType : popular or nowplaying or top rated or upcoming
     */
    void getMoviesCommon(String urlType, Callback<List<Movie>> callback);
}
