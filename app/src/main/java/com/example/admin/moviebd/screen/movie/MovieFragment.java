package com.example.admin.moviebd.screen.movie;

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

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.screen.BaseFragment;
import com.example.admin.moviebd.screen.main.MainActivity;
import com.example.admin.moviebd.utils.Constants;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.List;

import static com.example.admin.moviebd.screen.movie.PageDefault.NOW_PLAYING_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.PAGE_DEFAULT;
import static com.example.admin.moviebd.screen.movie.PageDefault.POPULAR_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.TOP_RATED_MOVIE;
import static com.example.admin.moviebd.screen.movie.PageDefault.UPCOMING_MOVIE;


public class MovieFragment extends BaseFragment implements MovieContract.View, MovieAdapter.OnMovieItemClickListener {
    private LinearLayout mLayoutLoading;
    private RecyclerView mMoviesPopular, mMovieNowPlaying, mMovieTopRated, mMovieUpcoming;
    private MoviePresenter mMoviePresenter;
    private MainActivity mMainActivity;
    private PopupMenu mMenuOptionMovie;

    public static MovieFragment newInstance() {
        MovieFragment movieFragment = new MovieFragment();
        return movieFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        mMoviePresenter = new MoviePresenter(this, Injection.getInstance().getMovieRepository());
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
        initView();
        mMoviePresenter.getMoviePopularFromApi(StringUtils.formatStringMovieUrl(Constants.FINAL_API_MOVIE,
                Constants.ApiAddContent.MOVIE_POPULAR, PAGE_DEFAULT));
    }

    private void initView() {
        mMoviesPopular = mMainActivity.findViewById(R.id.movies_popular);
        mMovieNowPlaying = mMainActivity.findViewById(R.id.movies_now_playing);
        mMovieTopRated = mMainActivity.findViewById(R.id.movies_top_rated);
        mMovieUpcoming = mMainActivity.findViewById(R.id.movies_upcoming);
        mLayoutLoading = mMainActivity.findViewById(R.id.dialog_loading);
    }

    private void createOptionMenuMovie(View view) {
        mMenuOptionMovie = new PopupMenu(mMainActivity, view);
        mMenuOptionMovie.getMenuInflater().inflate(R.menu.menu_option_movie, mMenuOptionMovie.getMenu());
        mMenuOptionMovie.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // TODO: 8/3/2018 hanlder event click item menu in item movie
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
                showDataMovie(movies, mMoviesPopular);
                break;
            case NOW_PLAYING_MOVIE:
                showDataMovie(movies, mMovieNowPlaying);
                break;
            case TOP_RATED_MOVIE:
                showDataMovie(movies, mMovieTopRated);
                break;
            case UPCOMING_MOVIE:
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
    public void onShowOption(View view) {
        createOptionMenuMovie(view);
    }
}
