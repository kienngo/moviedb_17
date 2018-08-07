package com.example.admin.moviebd.screen.genres.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.screen.genres.GenresActivity;
import com.example.admin.moviebd.screen.movie.MovieAdapter;
import com.example.admin.moviebd.utils.Constant;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.List;

public class ResultGenresFragment extends Fragment implements ResultGenresContract.View,
        MovieAdapter.OnMovieItemClickListener {
    private static final int SPAN_COUNT = 3;
    private static final String BUNDLE_GENRES = "BUNDLE_GENRES";
    private GenresActivity mGenresActivity;
    private RecyclerView mGenresResult;
    private LinearLayout mDialogLoading;
    private ResultGenresPresenter mResultGenresPresenter;
    private String mGenresQuery;

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
                Injection.getInstance().getGenresResultRepository());
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
        getData();
    }

    private void initView() {
        mGenresResult = mGenresActivity.findViewById(R.id.recycler_list_more);
        mDialogLoading = mGenresActivity.findViewById(R.id.dialog_loading);
    }

    private void getData() {
        mResultGenresPresenter.getGenresFromApi(StringUtils.formatStringSearchGenresUrl(
                Constant.FINAL_API_SEARCH_GENRES, Constant.ApiAddContent.SEARCH_GENRES_MOVIE,
                mGenresQuery, 1));
    }

    @Override
    public void onStartLoading() {
        mDialogLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        MovieAdapter mMovieAdapter = new MovieAdapter(mGenresActivity, movies, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mGenresActivity, SPAN_COUNT);
        mGenresResult.setLayoutManager(layoutManager);
        mGenresResult.setAdapter(mMovieAdapter);
    }

    @Override
    public void onFailed(Exception exception) {
        // TODO: 8/7/2018 show error
    }

    @Override
    public void onDismissLoading() {
        mDialogLoading.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int movieId) {
        // TODO: 8/7/2018 go to screen detail
    }

    @Override
    public void onShowOption(View view) {
        // TODO: 8/7/2018 show pop up menu
    }
}
