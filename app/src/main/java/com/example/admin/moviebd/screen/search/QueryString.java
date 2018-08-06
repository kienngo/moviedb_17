package com.example.admin.moviebd.screen.search;

import android.support.annotation.StringDef;

@StringDef({QueryString.SORT_BY, QueryString.YEAR, QueryString.KEY_PASSED_DATA, QueryString.KEY_SEARCH_CONDITION})
public @interface QueryString {
    String SORT_BY = "sort_by";
    String YEAR = "year";
    String KEY_PASSED_DATA = "TEXT_SEARCH";
    String KEY_SEARCH_CONDITION = "SEARCH_CONDITION";
}
