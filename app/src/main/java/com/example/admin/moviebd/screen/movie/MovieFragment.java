package com.example.admin.moviebd.screen.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.screen.BaseFragment;
import com.example.admin.moviebd.screen.movies.MoviesActivity;
import com.example.admin.moviebd.screen.main.MainActivity;
import com.example.admin.moviebd.utils.Constant;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.admin.moviebd.screen.movies.MoviesKey.EXTRA_MOVIE_LIST;
import static com.example.admin.moviebd.screen.movies.MoviesKey.EXTRA_MOVIE_URL;
import static com.example.admin.moviebd.screen.movie.PageDefault.NOW_PLAYING_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.PAGE_DEFAULT;
import static com.example.admin.moviebd.screen.movie.PageDefault.POPULAR_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.TOP_RATED_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.UPCOMING_MOVIE;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.MOVIE_NOWPLAYING;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.MOVIE_POPULAR;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.MOVIE_TOP_RATED;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.MOVIE_UPCOMING;


public class MovieFragment extends BaseFragment implements MovieContract.View,
        MovieAdapter.OnMovieItemClickListener, View.OnClickListener {
    private LinearLayout mLayoutLoading;
    private RecyclerView mMoviesPopular, mMovieNowPlaying, mMovieTopRated, mMovieUpcoming;
    private TextView mTextMoviePopular, mTextMoreNowPlaying, mTextMoreTopRated, mTextMoreUpcoming;
    private List<Movie> mPopular, mNowPlaying, mTopRated, mUpcoming;
    private MoviePresenter mMoviePresenter;
    private MainActivity mMainActivity;

    public static MovieFragment newInstance() {
        MovieFragment movieFragment = new MovieFragment();
        return movieFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        mMoviePresenter = new MoviePresenter(this,
                Injection.getInstance(mMainActivity).getMovieRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initView();
        setOnClickListener();
        mMoviePresenter.getMoviePopularFromApi(StringUtils.formatStringMovieUrl(
                Constant.FINAL_API_MOVIE, MOVIE_POPULAR, PAGE_DEFAULT));
    }

    private void initData() {
        mPopular = new ArrayList<>();
        mNowPlaying = new ArrayList<>();
        mTopRated = new ArrayList<>();
        mUpcoming = new ArrayList<>();
    }

    private void initView() {
        mMoviesPopular = mMainActivity.findViewById(R.id.movies_popular);
        mMovieNowPlaying = mMainActivity.findViewById(R.id.movies_now_playing);
        mMovieTopRated = mMainActivity.findViewById(R.id.movies_top_rated);
        mMovieUpcoming = mMainActivity.findViewById(R.id.movies_upcoming);
        mLayoutLoading = mMainActivity.findViewById(R.id.dialog_loading);
        mTextMoviePopular = mMainActivity.findViewById(R.id.text_more_popular);
        mTextMoreNowPlaying = mMainActivity.findViewById(R.id.text_more_now_playing);
        mTextMoreTopRated = mMainActivity.findViewById(R.id.text_more_top_rated);
        mTextMoreUpcoming = mMainActivity.findViewById(R.id.text_more_upcoming);
    }

    private void setOnClickListener() {
        mTextMoviePopular.setOnClickListener(this);
        mTextMoreNowPlaying.setOnClickListener(this);
        mTextMoreTopRated.setOnClickListener(this);
        mTextMoreUpcoming.setOnClickListener(this);
    }

    private void createOptionMenuMovie(View view, final Movie movie) {
        PopupMenu mMenuOptionMovie = new PopupMenu(mMainActivity, view);
        mMenuOptionMovie.getMenuInflater().inflate(R.menu.menu_option_movie,
                mMenuOptionMovie.getMenu());
        mMenuOptionMovie.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                StringUtils.checkInsertMovie(mMainActivity, mMoviePresenter.insertMovieLocal(movie));
                return true;
            }
        });
        mMenuOptionMovie.show();
    }

    private void showDataMovie(List<Movie> movies, RecyclerView recyclerView) {
        MovieAdapter mMovieAdapter = new MovieAdapter(mMainActivity, movies, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mMainActivity,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mMovieAdapter);
    }

    @Override
    public void onStartLoading() {
        mLayoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<Movie> movies, int typeMovie) {
        switch (typeMovie) {
            case POPULAR_MOVIE:
                mPopular = movies;
                showDataMovie(movies, mMoviesPopular);
                break;
            case NOW_PLAYING_MOVIE:
                mNowPlaying = movies;
                showDataMovie(movies, mMovieNowPlaying);
                break;
            case TOP_RATED_MOVIE:
                mTopRated = movies;
                showDataMovie(movies, mMovieTopRated);
                break;
            case UPCOMING_MOVIE:
                mUpcoming = movies;
                showDataMovie(movies, mMovieUpcoming);
                break;
        }
    }

    @Override
    public void onFailed(Exception exception, int typeMovieFail) {
        // TODO: 8/3/2018 show error
    }

    @Override
    public void onDismissLoading() {
        mLayoutLoading.setVisibility(View.GONE);
    }


    @Override
    public void onItemClick(int movieId) {

    }

    @Override
    public void onShowOption(View view, Movie movie) {
        createOptionMenuMovie(view, movie);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_more_popular:
                goToActivityLoadMore(EXTRA_MOVIE_URL, MOVIE_POPULAR, EXTRA_MOVIE_LIST, mPopular);
                break;
            case R.id.text_more_now_playing:
                goToActivityLoadMore(EXTRA_MOVIE_URL, MOVIE_NOWPLAYING, EXTRA_MOVIE_LIST, mNowPlaying);
                break;
            case R.id.text_more_top_rated:
                goToActivityLoadMore(EXTRA_MOVIE_URL, MOVIE_TOP_RATED, EXTRA_MOVIE_LIST, mTopRated);
                break;
            case R.id.text_more_upcoming:
                goToActivityLoadMore(EXTRA_MOVIE_URL, MOVIE_UPCOMING, EXTRA_MOVIE_LIST, mUpcoming);
                break;
        }
    }

    private void goToActivityLoadMore(String extraUrl, String valueUrl, String extraMovies, List<Movie> valueMovies) {
        Intent intent = new Intent(mMainActivity, MoviesActivity.class);
        intent.putExtra(extraUrl, valueUrl);
        intent.putExtra(extraMovies, (Serializable) valueMovies);
        startActivity(intent);
    }
}
