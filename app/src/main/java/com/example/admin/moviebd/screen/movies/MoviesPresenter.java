package com.example.admin.moviebd.screen.movies;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.repository.MovieRepository;

import java.util.List;

public class MoviesPresenter implements MoviesContract.Presenter {
    private MoviesContract.View mView;
    private MovieRepository mMovieRepository;

    public MoviesPresenter(MoviesContract.View view,
                           MovieRepository movieRepository) {
        this.mView = view;
        this.mMovieRepository = movieRepository;
    }

    @Override
    public void getMovieLoadMore(String url) {
        mMovieRepository.getMovieFromApi(url, new BaseDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccess((List<Movie>) data);
            }

            @Override
            public void onGetFailure(Exception exeption) {
                mView.onFailed(exeption);
            }

            @Override
            public void onComplete() {
                mView.onDismissLoading();
            }
        });
    }
}
