package com.example.admin.moviebd.screen.movie;

import android.support.annotation.IntDef;

@IntDef({PageDefault.PAGE_DEFAULT, PageDefault.POPULAR_MOVIE, PageDefault.NOW_PLAYING_MOVIE, PageDefault.TOP_RATED_MOVIE, PageDefault.UPCOMING_MOVIE})
public @interface PageDefault {
    int PAGE_DEFAULT = 1;
    int POPULAR_MOVIE = 101;
    int NOW_PLAYING_MOVIE = 102;
    int TOP_RATED_MOVIE = 103;
    int UPCOMING_MOVIE = 104;
}
