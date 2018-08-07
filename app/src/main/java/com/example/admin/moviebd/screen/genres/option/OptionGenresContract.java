package com.example.admin.moviebd.screen.genres.option;

import com.example.admin.moviebd.data.model.Genre;

import java.util.List;

public interface OptionGenresContract {
    interface View {
        void onStartLoading();

        void onSuccess(List<Genre> genres);

        void onFailed(Exception exception);

        void onDismissLoading();
    }

    interface Presenter {
        void getGenresFromApi(String url);
    }
}
