package com.example.admin.moviebd.screen.movies;

import android.support.annotation.StringDef;

@StringDef({MoviesKey.EXTRA_MOVIE_URL, MoviesKey.EXTRA_MOVIE_LIST})
public @interface MoviesKey {
    String EXTRA_MOVIE_URL = "com.example.admin.moviebd.EXTRA_MOVIE_URL";
    String EXTRA_MOVIE_LIST = "com.example.admin.moviebd.MOVIE_POPULAR_LIST";
}
