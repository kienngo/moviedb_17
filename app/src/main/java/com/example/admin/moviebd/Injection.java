package com.example.admin.moviebd;

import com.example.admin.moviebd.data.source.MovieRepository;
import com.example.admin.moviebd.data.source.remote.MovieRemoteDataSource;

public class Injection {
    private static Injection sInstance;

    public Injection(){

    }

    public static Injection getInstance(){
        if (sInstance == null){
            sInstance = new Injection();
        }
        return sInstance;
    }

    public MovieRepository getMovieRepository() {
        return MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance()
        );
    }
}
