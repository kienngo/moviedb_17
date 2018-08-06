package com.example.admin.moviebd.screen.search;

import android.support.annotation.IntDef;

@IntDef({SearchCondition.MULTI_SEARCH, SearchCondition.SEARCH_MOVIE, SearchCondition.SEARCH_TELEVISION})
public @interface SearchCondition {
    int SEARCH_MOVIE = 101;
    int SEARCH_TELEVISION = 102;
    int MULTI_SEARCH = 103;
}
