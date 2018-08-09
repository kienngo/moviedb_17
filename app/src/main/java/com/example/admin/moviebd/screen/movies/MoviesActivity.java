package com.example.admin.moviebd.screen.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.screen.BaseActivity;
import com.example.admin.moviebd.screen.RecyclerViewScrollListener;
import com.example.admin.moviebd.utils.Constant;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.moviebd.screen.movies.MoviesKey.EXTRA_MOVIE_LIST;
import static com.example.admin.moviebd.screen.movies.MoviesKey.EXTRA_MOVIE_URL;

public class MoviesActivity extends BaseActivity implements MoviesContract.View,
        MoviesAdapter.OnMovieItemClickListener {
    private RecyclerView mMovieLoadMore;
    private Toolbar mToolbarLoadMore;
    private MoviesAdapter mMoviesAdapter;
    private List<Movie> mMovies;
    private String mUrlContent;
    private int mCurrentPage;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerViewScrollListener mRecyclerViewScrollListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_more);
        initData();
        initView();
        setToolbar();
        showData();
        setOnScrollRecyclerView();
    }

    @SuppressWarnings(value = "unchecked")
    private void initData() {
        mMovies = new ArrayList<>();
        mMovies = (List<Movie>) getIntent().getSerializableExtra(EXTRA_MOVIE_LIST);
        mUrlContent = getIntent().getStringExtra(EXTRA_MOVIE_URL);
    }

    private void initView() {
        mMovieLoadMore = findViewById(R.id.recycler_loading_more);
        mToolbarLoadMore = findViewById(R.id.toolbar_loading_more);
    }

    private void setToolbar() {
        mToolbarLoadMore.setTitle(R.string.data_loading_more);
        setSupportActionBar(mToolbarLoadMore);
    }

    private void showData() {
        mMoviesAdapter = new MoviesAdapter(this, mMovies, this);
        mLinearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mMovieLoadMore.setLayoutManager(mLinearLayoutManager);
        mMovieLoadMore.setAdapter(mMoviesAdapter);
    }

    private void setOnScrollRecyclerView() {
        mRecyclerViewScrollListener = new RecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                mCurrentPage = page;
                loadMoreDataMovie(StringUtils.formatStringMovieUrl(Constant.FINAL_API_MOVIE, mUrlContent, mCurrentPage));
            }
        };
        mMovieLoadMore.addOnScrollListener(mRecyclerViewScrollListener);
    }

    private void loadMoreDataMovie(String url) {
        MoviesPresenter mMoviesPresenter = new MoviesPresenter(this,
                Injection.getInstance().getMovieRepository());
        mMoviesPresenter.getMovieLoadMore(url);
    }

    @Override
    public void onStartLoading() {
        mMoviesAdapter.addLoadingView();
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        mMoviesAdapter.removeLoadingView();
        mMoviesAdapter.addData(movies);
    }

    @Override
    public void onFailed(Exception exception) {
    }

    @Override
    public void onDismissLoading() {
        mRecyclerViewScrollListener.resetStateLoading();
    }

    @Override
    public void onOptionClickListener(Movie movie) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
