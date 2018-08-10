package com.example.admin.moviebd.screen.genres.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.admin.moviebd.screen.RecyclerViewScrollListener;
import com.example.admin.moviebd.screen.genres.GenresActivity;
import com.example.admin.moviebd.screen.movie.MovieAdapter;
import com.example.admin.moviebd.utils.Constant;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ResultGenresFragment extends Fragment implements ResultGenresContract.View,
        MovieAdapter.OnMovieItemClickListener {
    private static final String BUNDLE_GENRES = "BUNDLE_GENRES";
    private static final int SPAN_COUNT = 3;
    private GenresActivity mGenresActivity;
    private RecyclerView mGenresResult;
    private LinearLayout mDialogLoading;
    private ResultGenresPresenter mResultGenresPresenter;
    private RecyclerViewScrollListener mRecyclerViewScrollListener;
    private LinearLayoutManager mLinearLayoutManager;
    private List<Movie> mMovies = new ArrayList<>();
    private String mGenresQuery;
    private int mCurrentPage;

    public static ResultGenresFragment newInstance(String strGenres) {
        ResultGenresFragment resultGenresFragment = new ResultGenresFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_GENRES, strGenres);
        resultGenresFragment.setArguments(bundle);
        return resultGenresFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenresActivity = (GenresActivity) getActivity();
        mResultGenresPresenter = new ResultGenresPresenter(this,
                Injection.getInstance(mGenresActivity).getGenresResultRepository());
        Bundle bundle = getArguments();
        if (bundle != null) {
            mGenresQuery = bundle.getString(BUNDLE_GENRES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_for_genres, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        setOnScrollRecyclerView();
        getData(1);
    }

    private void initView() {
        mGenresResult = mGenresActivity.findViewById(R.id.recycler_list_more);
        mDialogLoading = mGenresActivity.findViewById(R.id.dialog_loading);
    }

    private void getData(int pageNumber) {
        mResultGenresPresenter.getGenresFromApi(StringUtils.formatStringSearchGenresUrl(
                Constant.FINAL_API_SEARCH_GENRES, Constant.ApiAddContent.SEARCH_GENRES_MOVIE,
                mGenresQuery, pageNumber));
    }

    private void setOnScrollRecyclerView() {
        mLinearLayoutManager = new GridLayoutManager(mGenresActivity, SPAN_COUNT);
        mRecyclerViewScrollListener = new RecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                mCurrentPage = page;
                getData(mCurrentPage);
            }
        };
        mGenresResult.addOnScrollListener(mRecyclerViewScrollListener);
    }

    private void createOptionMenuMovie(View view, final Movie movie) {
        PopupMenu mMenuOptionMovie = new PopupMenu(mGenresActivity, view);
        mMenuOptionMovie.getMenuInflater().inflate(R.menu.menu_option_movie,
                mMenuOptionMovie.getMenu());
        mMenuOptionMovie.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                StringUtils.checkInsertMovie(mGenresActivity, mResultGenresPresenter.insertMovieLocal(movie));
                return true;
            }
        });
        mMenuOptionMovie.show();
    }

    @Override
    public void onStartLoading() {
        mDialogLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        mMovies.addAll(movies);
        MovieAdapter mMovieAdapter = new MovieAdapter(mGenresActivity, mMovies, this);
        mGenresResult.setLayoutManager(mLinearLayoutManager);
        mGenresResult.setAdapter(mMovieAdapter);
        mGenresResult.scrollToPosition((mCurrentPage - Constant.COUNT) * Constant.SIZE_START);
    }

    @Override
    public void onFailed(Exception exception) {
        // TODO: 8/7/2018 show error
    }

    @Override
    public void onDismissLoading() {
        mDialogLoading.setVisibility(View.GONE);
        mRecyclerViewScrollListener.resetStateLoading();
    }

    @Override
    public void onItemClick(int movieId) {
        // TODO: 8/7/2018 go to screen detail
    }

    @Override
    public void onShowOption(View view, Movie movie) {
        createOptionMenuMovie(view, movie);
    }
}
