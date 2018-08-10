package com.example.admin.moviebd.screen.genres.result;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.source.SearchResultDataSource;
import com.example.admin.moviebd.data.source.repository.GenresResultRepository;

import java.util.List;

public class ResultGenresPresenter implements ResultGenresContract.Presenter {
    private ResultGenresContract.View mView;
    private GenresResultRepository mGenresResultRepository;

    public ResultGenresPresenter(ResultGenresContract.View view,
                                 GenresResultRepository genresResultRepository) {
        this.mView = view;
        this.mGenresResultRepository = genresResultRepository;
    }

    @Override
    public void getGenresFromApi(String url) {
        mGenresResultRepository.getGenresFromApi(url, new SearchResultDataSource.Callback() {
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

    @Override
    public boolean insertMovieLocal(Movie movie) {
        if (isFavoritesLocal(String.valueOf(movie.getId()))){
            return false;
        }else{
            return mGenresResultRepository.insertMovie(movie);
        }
    }

    @Override
    public boolean isFavoritesLocal(String movieId) {
        return mGenresResultRepository.isFavouriteMovie(movieId);
    }
}
