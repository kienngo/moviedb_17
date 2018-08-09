package com.example.admin.moviebd.data.source;

import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.data.model.Recommendation;
import com.example.admin.moviebd.data.model.Trailer;

import java.util.List;

public interface MovieDetailDataSource extends BaseDataSource {
    void getMovieInfor(String urlType, Callback<MovieInformation> callback);

    void getGenres(String urlType, Callback<List<Genres>> callback);

    void getCompany(String urlType, Callback<List<ProductionCompany>> callback);

    void getTrailer(String urlType, Callback<List<Trailer>> callback);

    void getRecommendation(String urlType, Callback<List<MovieRecommendation>> callback);
}
