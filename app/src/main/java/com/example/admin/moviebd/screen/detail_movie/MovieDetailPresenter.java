package com.example.admin.moviebd.screen.detail_movie;

import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.data.source.MovieDetailDataSource;
import com.example.admin.moviebd.data.source.repository.MovieDetailRepository;

import java.util.List;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {
    private MovieDetailContract.View mView;
    private MovieDetailRepository mRepository;

    public MovieDetailPresenter(MovieDetailContract.View view, MovieDetailRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getMovieInformationFromApi(String url) {
        mRepository.getMovieInforFromApi(url, new MovieDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessMovieInfor((MovieInformation) data);
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

    @Override
    public void getGenresFromApi(String url) {
        mRepository.getGenresFromApi(url, new MovieDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessGenres((List<Genres>) data);
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

    @Override
    public void getCompanyFromApi(String url) {
        mRepository.getCompanyFromApi(url, new MovieDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessCompany((List<ProductionCompany>) data);
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

    @Override
    public void getTrailerFromApi(String url) {
        mRepository.getTrailerFromApi(url, new MovieDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessTrailer((List<Trailer>) data);
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

    @Override
    public void getRecommendationFromApi(String url) {
        mRepository.getRecommendationFromApi(url, new MovieDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessRecommendation((List<MovieRecommendation>) data);
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
