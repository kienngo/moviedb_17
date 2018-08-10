package com.example.admin.moviebd;

import android.content.Context;

import com.example.admin.moviebd.data.source.local.MovieLocalDataSource;
import com.example.admin.moviebd.data.source.remote.GenresRemoteOptionDataSource;
import com.example.admin.moviebd.data.source.remote.GenresRemoteResultDataSouce;
import com.example.admin.moviebd.data.source.remote.MovieInforRemoteDataSource;
import com.example.admin.moviebd.data.source.remote.SearchResultRemoteDataSource;
import com.example.admin.moviebd.data.source.repository.GenresOptionRepository;
import com.example.admin.moviebd.data.source.repository.GenresResultRepository;
import com.example.admin.moviebd.data.source.repository.MovieDetailRepository;
import com.example.admin.moviebd.data.source.repository.MovieRepository;
import com.example.admin.moviebd.data.source.remote.MovieRemoteDataSource;
import com.example.admin.moviebd.data.source.repository.SearchResultRepository;

public class Injection {
    private static Injection sInstance;
    private Context mContext;

    private Injection(Context context) {
        this.mContext = context;
    }

    public static Injection getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Injection(context);
        }
        return sInstance;
    }

    public MovieRepository getMovieRepository() {
        return MovieRepository.getInstance(MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(mContext));
    }

    public SearchResultRepository getSearchResultRepository() {
        return SearchResultRepository.getInstance(
                SearchResultRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(mContext)
        );
    }

    public GenresOptionRepository getGenresRepository() {
        return GenresOptionRepository.getInstance(
                GenresRemoteOptionDataSource.getInstance()
        );
    }

    public GenresResultRepository getGenresResultRepository() {
        return GenresResultRepository.getInstance(
                GenresRemoteResultDataSouce.getInstance(),
                MovieLocalDataSource.getInstance(mContext)
        );
    }

    public MovieDetailRepository getMovieDetailRepository() {
        return MovieDetailRepository.getInstance(
                MovieInforRemoteDataSource.getInstance()
        );
    }
}
