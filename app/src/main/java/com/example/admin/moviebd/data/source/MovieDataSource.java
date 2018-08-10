package com.example.admin.moviebd.data.source;


import com.example.admin.moviebd.data.model.Movie;

import java.util.List;

public interface MovieDataSource extends BaseDataSource {
    interface RemoteDataSource {
        /*
        urlType : popular or nowplaying or top rated or upcoming
        */
        void getMoviesCommon(String urlType, Callback<List<Movie>> callback);
    }

    interface LocalDataSource{
        List<Movie> getListMovies();
        boolean insertMovieLocal(Movie movie);
        boolean deleteMovieLocal(Movie movie);
        boolean isFavouriteMovie(String movieId);
    }
}
