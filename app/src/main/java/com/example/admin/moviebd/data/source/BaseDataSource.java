package com.example.admin.moviebd.data.source;

public interface BaseDataSource {
    interface Callback<T> {
        void onStartLoading();

        void onGetSuccess(T data);

        void onGetFailure(Exception exeption);

        void onComplete();
    }
}
