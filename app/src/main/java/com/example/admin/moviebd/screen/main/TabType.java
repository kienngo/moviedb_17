package com.example.admin.moviebd.screen.main;

import android.support.annotation.IntDef;

@IntDef({TabType.MOVIE_FRAGMENT, TabType.TELEVISION_FRAGMENT, TabType.TAB_NUMBER_FRAGMENT})
public @interface TabType {
    int MOVIE_FRAGMENT = 0;
    int TELEVISION_FRAGMENT = 1;
    int TAB_NUMBER_FRAGMENT = 2;
}
