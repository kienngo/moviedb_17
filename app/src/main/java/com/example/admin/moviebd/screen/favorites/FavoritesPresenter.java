package com.example.admin.moviebd.screen.favorites;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.repository.MovieRepository;

public class FavoritesPresenter implements FavoritesContract.Presenter {
    private FavoritesContract.View mView;
    private MovieRepository mMovieRepository;

    public FavoritesPresenter(FavoritesContract.View view, MovieRepository movieRepository){
        this.mView = view;
        this.mMovieRepository = movieRepository;
    }

    @Override
    public void getMovieFromLocal() {
        mView.showMovieFromLocal(mMovieRepository.getMoviesLocal());
    }

    @Override
    public boolean deleteMovie(Movie movie) {
        return mMovieRepository.deleteMovie(movie);
    }
}
