package com.example.admin.moviebd.screen.movie;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.MovieDataSource;
import com.example.admin.moviebd.data.source.MovieRepository;
import com.example.admin.moviebd.utils.Constants;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.List;

import static com.example.admin.moviebd.screen.movie.PageDefault.NOW_PLAYING_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.PAGE_DEFAULT;
import static com.example.admin.moviebd.screen.movie.PageDefault.POPULAR_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.TOP_RATED_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.UPCOMING_MOVIE;

public class MoviePresenter implements MovieContract.Presenter {
    private MovieContract.View mView;
    private MovieRepository mMovieRepository;

    public MoviePresenter(MovieContract.View view, MovieRepository movieRepository) {
        this.mView = view;
        this.mMovieRepository = movieRepository;
    }

    @Override
    public void getMoviePopularFromApi(String url) {
        mMovieRepository.getMovieFromApi(url, new MovieDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccess((List<Movie>) data, POPULAR_MOVIE);
            }

            @Override
            public void onGetFailure(Exception exception) {
                // TODO: 8/3/2018 get the next data about movie nowplaying when get movie popular failure
                mView.onFailed(exception, POPULAR_MOVIE);
                getMovieNowplayingFromApi(StringUtils.formatStringUrl(Constants.FINAL_API_MOVIE,
                        Constants.ApiAddContent.MOVIE_NOWPLAYING, PAGE_DEFAULT));
            }

            @Override
            public void onComplete() {
                // TODO: 8/3/2018 get the next data about movie nowplaying when get movie popular complete
                getMovieNowplayingFromApi(StringUtils.formatStringUrl(Constants.FINAL_API_MOVIE,
                        Constants.ApiAddContent.MOVIE_NOWPLAYING, PAGE_DEFAULT));
            }
        });
    }

    @Override
    public void getMovieNowplayingFromApi(String url) {
        mMovieRepository.getMovieFromApi(url, new MovieDataSource.Callback() {
            @Override
            public void onStartLoading() {
                // TODO: 8/3/2018 do nothing
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccess((List<Movie>) data, NOW_PLAYING_MOVIE);
            }

            @Override
            public void onGetFailure(Exception exception) {
                mView.onFailed(exception, NOW_PLAYING_MOVIE);
                getMovieTopRatedFromApi(StringUtils.formatStringUrl(Constants.FINAL_API_MOVIE,
                        Constants.ApiAddContent.MOVIE_TOP_RATED, PAGE_DEFAULT));
            }

            @Override
            public void onComplete() {
                getMovieTopRatedFromApi(StringUtils.formatStringUrl(Constants.FINAL_API_MOVIE,
                        Constants.ApiAddContent.MOVIE_TOP_RATED, PAGE_DEFAULT));
            }
        });
    }

    @Override
    public void getMovieTopRatedFromApi(String url) {
        mMovieRepository.getMovieFromApi(url, new MovieDataSource.Callback() {
            @Override
            public void onStartLoading() {
                // TODO: 8/3/2018 do nothing
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccess((List<Movie>) data, TOP_RATED_MOVIE);
            }

            @Override
            public void onGetFailure(Exception exception) {
                mView.onFailed(exception, TOP_RATED_MOVIE);
                getMovieUpcomingFromApi(StringUtils.formatStringUrl(Constants.FINAL_API_MOVIE,
                        Constants.ApiAddContent.MOVIE_UPCOMING, PAGE_DEFAULT));
            }

            @Override
            public void onComplete() {
                getMovieUpcomingFromApi(StringUtils.formatStringUrl(Constants.FINAL_API_MOVIE,
                        Constants.ApiAddContent.MOVIE_UPCOMING, PAGE_DEFAULT));
            }
        });
    }

    @Override
    public void getMovieUpcomingFromApi(String url) {
        mMovieRepository.getMovieFromApi(url, new MovieDataSource.Callback() {
            @Override
            public void onStartLoading() {
                // TODO: 8/3/2018 do nothing
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccess((List<Movie>) data, UPCOMING_MOVIE);
            }

            @Override
            public void onGetFailure(Exception exception) {
                mView.onFailed(exception, UPCOMING_MOVIE);
            }

            @Override
            public void onComplete() {
                // TODO: 8/3/2018 dissmiss dialog when all data ready display
                mView.onDismissLoading();
            }
        });
    }
}
