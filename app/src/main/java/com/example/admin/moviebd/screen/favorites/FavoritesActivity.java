package com.example.admin.moviebd.screen.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.screen.BaseActivity;
import com.example.admin.moviebd.screen.movie.MovieAdapter;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.List;

public class FavoritesActivity extends BaseActivity implements FavoritesContract.View,
        MovieAdapter.OnMovieItemClickListener {
    private static final int SPAN_COUNT = 3;
    private RecyclerView mFavorites;
    private Toolbar mToolbarFavorites;
    private FavoritesPresenter mFavoritesPresenter;
    private MovieAdapter mMovieAdapter;
    private List<Movie> mMovies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initView();
        setToolbar();
        initData();
    }

    private void initView() {
        mFavorites = findViewById(R.id.recycler_favorites);
        mToolbarFavorites = findViewById(R.id.toolbar_favorites);
    }

    private void setToolbar() {
        mToolbarFavorites.setTitle(R.string.title_favorites);
        mToolbarFavorites.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbarFavorites);
    }

    private void initData() {
        mFavoritesPresenter = new FavoritesPresenter(this,
                Injection.getInstance(this).getMovieRepository());
        mFavoritesPresenter.getMovieFromLocal();
    }

    private void createOptionMenuMovie(final View view, final Movie movie) {
        PopupMenu mMenuOptionMovie = new PopupMenu(this, view);
        mMenuOptionMovie.getMenuInflater().inflate(R.menu.menu_option_favories,
                mMenuOptionMovie.getMenu());
        mMenuOptionMovie.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                StringUtils.checkDeleteMovie(view.getContext(),
                        mFavoritesPresenter.deleteMovie(movie));
                mMovies.remove(mMovieAdapter.indexMovieDelete(movie));
                mMovieAdapter.notifyDataSetChanged();
                return true;
            }
        });
        mMenuOptionMovie.show();
    }

    @Override
    public void showMovieFromLocal(List<Movie> movies) {
        mMovies = movies;
        mMovieAdapter = new MovieAdapter(FavoritesActivity.this,
                movies, this);
        RecyclerView.LayoutManager layoutManager = new
                GridLayoutManager(FavoritesActivity.this, SPAN_COUNT);
        mFavorites.setLayoutManager(layoutManager);
        mFavorites.setAdapter(mMovieAdapter);
    }

    @Override
    public void onItemClick(int movieId) {

    }

    @Override
    public void onShowOption(View view, Movie movie) {
        createOptionMenuMovie(view, movie);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
