package com.example.admin.moviebd.utils;

import com.example.admin.moviebd.BuildConfig;

public class Constant {
    public static final int SIZE_START = 20;
    public static final int COUNT = 1;
    public static class BaseApiUrl {
        public static final String METHOD_REQUEST_API = "GET";
        public static final String API_URL = "https://api.themoviedb.org/3/";
        public static final String API_URL_TV = "https://api.themoviedb.org/3/tv/%d";
        public static final String API_URL_MOVIE = "https://api.themoviedb.org/3/movie/%d";
        public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500/%s";
        public static final String ID_TV = "ID_TV";
        public static final String ID_MOVIE = "ID_MOVIE";
        public static final String ID_YOUTUBE = "ID_YOUTUBE";
    }

    public @interface ApiAddContent {
        String MOVIE_POPULAR = "movie/popular";
        String MOVIE_NOWPLAYING = "movie/now_playing";
        String MOVIE_TOP_RATED = "movie/top_rated";
        String MOVIE_UPCOMING = "movie/upcoming";
        String SEARCH_MULTI = "search/multi";
        String SEARCH_MOVIE = "search/movie";
        String SEARCH_TELEVISION = "search/tv";
        String GENRES_MOVIE = "genre/movie/list";
        String GENRES_TELEVISION = "genre/tv/list";
        String SEARCH_GENRES_MOVIE = "discover/movie";
    }

    // TODO: 8/5/2018 Final API MOVIE
    public static final String FINAL_API_MOVIE = BaseApiUrl.API_URL + "%s?api_key="
            + BuildConfig.API_KEY + "&language=en-US&page=%d";
    // TODO: 8/5/2018 Final API SEARCH
    public static final String FINAL_API_SEARCH = BaseApiUrl.API_URL + "%s?api_key="
            + BuildConfig.API_KEY + "&language=en-US&query=%s&page=%d";
    // TODO: 8/6/2018 Final API GENRES
    public static final String FINAL_API_GENRES = BaseApiUrl.API_URL + "%s?api_key="
            + BuildConfig.API_KEY + "&language=en-US";
    // TODO: 8/7/2018 Final API SEARCH FOR GENRES
    public static final String FINAL_API_SEARCH_GENRES = BaseApiUrl.API_URL + "%s?api_key="
            + BuildConfig.API_KEY + "&language=en-US&with_genres=%s&page=%d";

    public static final String FINAL_API_TV_VIDEO = BaseApiUrl.API_URL_TV
            + "/videos?api_key=" + BuildConfig.API_KEY + "&language=en-US";
    public static final String FINAL_API_TV_DETAIL = BaseApiUrl.API_URL_TV
            + "?api_key=" + BuildConfig.API_KEY + "&language=en-US";
    public static final String FINAL_API_TV_RECOMMENTDATIONS = BaseApiUrl.API_URL_TV
            + "/recommendations?api_key=" + BuildConfig.API_KEY + "&language=en-US";

    public static final String FINAL_API_MOVIE_VIDEO = BaseApiUrl.API_URL_MOVIE
            + "/videos?api_key=" + BuildConfig.API_KEY + "&language=en-US";
    public static final String FINAL_API_MOVIE_DETAIL = BaseApiUrl.API_URL_MOVIE
            + "?api_key=" + BuildConfig.API_KEY + "&language=en-US";
    public static final String FINAL_API_MOVIE_RECOMMENTDATIONS = BaseApiUrl.API_URL_MOVIE
            + "/recommendations?api_key=" + BuildConfig.API_KEY + "&language=en-US";

}
