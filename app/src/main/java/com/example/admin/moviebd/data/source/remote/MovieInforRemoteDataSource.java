package com.example.admin.moviebd.data.source.remote;

import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.data.source.MovieDetailDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieInforRemoteDataSource implements MovieDetailDataSource {
    private static MovieInforRemoteDataSource sInstance;

    private MovieInforRemoteDataSource() {

    }

    public static MovieInforRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieInforRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getMovieInfor(String urlType, final Callback<MovieInformation> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    MovieInformation movie = new MovieInformation(jsonObject);

                    callback.onGetSuccess(movie);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }


            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getGenres(String urlType, final Callback<List<Genres>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Genres> genres = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Genres.NameParseUrl.GENRES);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objectGenres = jsonArray.getJSONObject(i);
                        genres.add(new Genres(objectGenres));
                    }
                    callback.onGetSuccess(genres);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }


            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getCompany(String urlType, final Callback<List<ProductionCompany>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<ProductionCompany> companies = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(ProductionCompany.NameParseUrl.COMPANIES);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objectCompany = jsonArray.getJSONObject(i);
                        companies.add(new ProductionCompany(objectCompany));
                    }
                    callback.onGetSuccess(companies);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }


            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getTrailer(String urlType, final Callback<List<Trailer>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Trailer> trailers = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Trailer.NameParseUrl.RESULT);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Trailer trailer = new Trailer(jsonArray.getJSONObject(i));
                        trailers.add(trailer);
                    }

                    callback.onGetSuccess(trailers);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }

            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getRecommendation(String urlType, final Callback<List<MovieRecommendation>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<MovieRecommendation> movieRecommendations = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(MovieRecommendation.NameParseUrl.RESULT);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        MovieRecommendation movieRecommendation = new MovieRecommendation(jsonArray.getJSONObject(i));
                        movieRecommendations.add(movieRecommendation);
                    }

                    callback.onGetSuccess(movieRecommendations);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }

            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }
}
