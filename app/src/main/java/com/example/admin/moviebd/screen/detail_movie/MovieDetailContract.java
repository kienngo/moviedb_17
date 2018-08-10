package com.example.admin.moviebd.screen.detail_movie;

import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.data.model.Recommendation;
import com.example.admin.moviebd.data.model.Trailer;

import java.util.List;

public interface MovieDetailContract {
    interface View {
        void onStartLoading();

        void onSuccessMovieInfor(MovieInformation movie);

        void onSuccessGenres(List<Genres> genres);

        void onSuccessCompany(List<ProductionCompany> companies);

        void onSuccessTrailer(List<Trailer> trailers);

        void onSuccessRecommendation(List<MovieRecommendation> recommendations);

        void onFailed(Exception e);

        void onDismissLoading();
    }

    interface Presenter {
        void getMovieInformationFromApi(String url);

        void getGenresFromApi(String url);

        void getCompanyFromApi(String url);

        void getTrailerFromApi(String url);

        void getRecommendationFromApi(String url);
    }
}
