package com.example.admin.moviebd.screen.search.result;

import android.support.annotation.StringDef;

@StringDef({MediaType.MEDIA_TYPE_MOVIE, MediaType.MEDIA_TYPE_TV})
public @interface MediaType {
    String MEDIA_TYPE_MOVIE = "movie";
    String MEDIA_TYPE_TV = "tv";
}
