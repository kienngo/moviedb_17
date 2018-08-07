package com.example.admin.moviebd.screen.genres.option;

import com.example.admin.moviebd.data.model.Genre;
import com.example.admin.moviebd.data.source.SearchResultDataSource;
import com.example.admin.moviebd.data.source.repository.GenresOptionRepository;

import java.util.List;

public class OptionGenresPresenter implements OptionGenresContract.Presenter {
    private OptionGenresContract.View mView;
    private GenresOptionRepository mGenresOptionRepository;

    public OptionGenresPresenter(OptionGenresContract.View view, GenresOptionRepository genresOptionRepository) {
        this.mView = view;
        this.mGenresOptionRepository = genresOptionRepository;
    }

    @Override
    public void getGenresFromApi(String url) {
        mGenresOptionRepository.getGenresFromApi(url, new SearchResultDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccess((List<Genre>) data);
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
