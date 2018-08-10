package com.example.admin.moviebd.screen.detail_movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.screen.detail_tv.TrailerAdapter;
import com.example.admin.moviebd.screen.trailer.TrailerActivity;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View,
        MovieRecommendationAdapter.ItemClickMovieRecommendation, TrailerAdapter.ItemTrailerClick {
    private int mIdMovie;
    private LinearLayout mLayoutLoading;
    private ImageView mImageVideo, mImageBackground;
    private TextView mTextNameVideo, mTextDateVideo, mTextGenres,
            mTextVoteVideo, mTextOverViewVideo;
    private RecyclerView mRecyclerRecommendations, mRecyclerTrailer, mRecyclerCompany;
    private MovieDetailPresenter mDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        init();
        setLayoutRecycler();
        mIdMovie = getIntent().getIntExtra(Constant.BaseApiUrl.ID_MOVIE, 353081);
        mDetailPresenter = new MovieDetailPresenter(this,
                Injection.getInstance(this).getMovieDetailRepository());
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMovieDetail();
    }

    @Override
    public void onStartLoading() {
        mLayoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessMovieInfor(MovieInformation movie) {
        mTextNameVideo.setText(movie.getTitle());
        mTextDateVideo.setText(movie.getDate());
        mTextOverViewVideo.setText(movie.getOverview());
        mTextVoteVideo.setText(String.valueOf(movie.getVote()));
        Picasso.get()
                .load(String.format(Constant.BaseApiUrl.IMAGE_URL, movie.getPathImage()))
                .placeholder(R.drawable.image_no_image)
                .error(R.drawable.image_error)
                .into(mImageVideo);
        Picasso.get()
                .load(String.format(Constant.BaseApiUrl.IMAGE_URL, movie.getPathImage()))
                .placeholder(R.drawable.image_no_image)
                .error(R.drawable.image_error)
                .resize(mImageBackground.getWidth(), mImageBackground.getHeight())
                .into(mImageBackground);
    }

    @Override
    public void onSuccessGenres(List<Genres> genres) {
        String nameGenres = "";
        for (int i = 0; i < genres.size(); i++) {
            nameGenres = nameGenres + genres.get(i).getName() + "\n";
        }
        mTextGenres.setText(nameGenres);
    }

    @Override
    public void onSuccessCompany(List<ProductionCompany> companies) {
        mRecyclerCompany.setAdapter(new MovieCompanyAdapter(companies, MovieDetailActivity.this));
    }

    @Override
    public void onSuccessTrailer(List<Trailer> trailers) {
        mRecyclerTrailer.setAdapter(new TrailerAdapter(trailers, MovieDetailActivity.this, this));
    }

    @Override
    public void onSuccessRecommendation(List<MovieRecommendation> recommendations) {
        mRecyclerRecommendations.setAdapter(new MovieRecommendationAdapter
                (recommendations, MovieDetailActivity.this, this));
    }

    @Override
    public void onFailed(Exception e) {
        getMovieDetail();
    }

    @Override
    public void onDismissLoading() {
        mLayoutLoading.setVisibility(View.GONE);
    }

    private void init() {
        mTextGenres = (TextView) findViewById(R.id.text_genres);
        mLayoutLoading = (LinearLayout) findViewById(R.id.dialog_loading);
        mTextNameVideo = (TextView) findViewById(R.id.text_movie_name);
        mTextDateVideo = (TextView) findViewById(R.id.text_date);
        mTextVoteVideo = (TextView) findViewById(R.id.text_vote);
        mTextOverViewVideo = (TextView) findViewById(R.id.text_overview);
        mImageVideo = (ImageView) findViewById(R.id.image_movie);
        mImageBackground = (ImageView) findViewById(R.id.image_background);
        mRecyclerRecommendations = (RecyclerView) findViewById(R.id.recycler_recommendations);
        mRecyclerTrailer = (RecyclerView) findViewById(R.id.recycler_trailer);
        mRecyclerCompany = (RecyclerView) findViewById(R.id.recycler_company);
    }

    private void setLayoutRecycler() {
        mRecyclerRecommendations.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerCompany.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerTrailer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void getMovieDetail() {
        mDetailPresenter.getMovieInformationFromApi(String.format(Constant.FINAL_API_MOVIE_DETAIL, mIdMovie));
        mDetailPresenter.getGenresFromApi(String.format(Constant.FINAL_API_MOVIE_DETAIL, mIdMovie));
        mDetailPresenter.getCompanyFromApi(String.format(Constant.FINAL_API_MOVIE_DETAIL, mIdMovie));
        mDetailPresenter.getTrailerFromApi(String.format(Constant.FINAL_API_MOVIE_VIDEO, mIdMovie));
        mDetailPresenter.getRecommendationFromApi(String.format(Constant.FINAL_API_MOVIE_RECOMMENTDATIONS, mIdMovie));
    }

    @Override
    public void onItemClick(int idMovie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constant.BaseApiUrl.ID_MOVIE, idMovie);
        startActivity(intent);
    }

    @Override
    public void onClickTrailer(String idTrailer) {
        Intent intent = new Intent(this, TrailerActivity.class);
        intent.putExtra(Constant.BaseApiUrl.ID_YOUTUBE, idTrailer);
        startActivity(intent);
    }
}
