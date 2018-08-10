package com.example.admin.moviebd.screen.search.result;

import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.model.SearchResult;
import com.example.admin.moviebd.data.source.SearchResultDataSource;
import com.example.admin.moviebd.data.source.repository.SearchResultRepository;

import java.util.List;

public class ResultSearchPresenter implements ResultSearchContact.Presenter {
    private ResultSearchContact.View mView;
    private SearchResultRepository mSearchResultRepository;

    public ResultSearchPresenter(ResultSearchContact.View view,
                                 SearchResultRepository searchResultRepository) {
        this.mView = view;
        this.mSearchResultRepository = searchResultRepository;
    }

    @Override
    public void getDataSearchFromUrl(String url) {
        mSearchResultRepository.getSearchResultFromApi(url, new SearchResultDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccess((List<SearchResult>) data);
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
        if (isFavoritesLocal(String.valueOf(movie.getId()))) {
            return false;
        } else {
            return mSearchResultRepository.insertMovie(movie);
        }
    }

    @Override
    public boolean isFavoritesLocal(String movieId) {
        return mSearchResultRepository.isFavouriteMovie(movieId);
    }
}
