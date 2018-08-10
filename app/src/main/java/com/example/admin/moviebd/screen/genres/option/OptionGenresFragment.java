package com.example.admin.moviebd.screen.genres.option;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Genre;
import com.example.admin.moviebd.screen.BaseFragment;
import com.example.admin.moviebd.screen.genres.GenresActivity;
import com.example.admin.moviebd.screen.genres.result.ResultGenresFragment;
import com.example.admin.moviebd.utils.Constant;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.List;

public class OptionGenresFragment extends BaseFragment implements View.OnClickListener, OptionGenresContract.View {
    private static final int SPAN_COUNT = 2;
    private static final int LAST_ELEMENT = 1;
    private LinearLayout mLinearLoading;
    private RecyclerView mGenres;
    private Button mButtonSearch;
    private List<Genre> mListGenres;
    private GenresActivity mGenresActivity;

    public static OptionGenresFragment newInstance() {
        OptionGenresFragment optionGenresFragment = new OptionGenresFragment();
        return optionGenresFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenresActivity = (GenresActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genres, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        setOnClickListener();
        getData();
    }

    private void initView() {
        mButtonSearch = mGenresActivity.findViewById(R.id.button_search_genres);
        mLinearLoading = mGenresActivity.findViewById(R.id.dialog_loading);
        mGenres = mGenresActivity.findViewById(R.id.recycler_genres);
    }

    private void setOnClickListener() {
        mButtonSearch.setOnClickListener(this);
    }

    private void getData() {
        OptionGenresPresenter mOptionGenresPresenter = new OptionGenresPresenter(this,
                Injection.getInstance(mGenresActivity).getGenresRepository());
        mOptionGenresPresenter.getGenresFromApi(StringUtils.formatStringGenresUrl(
                Constant.FINAL_API_GENRES, Constant.ApiAddContent.GENRES_MOVIE));
    }

    @Override
    public void onStartLoading() {
        mLinearLoading.setVisibility(View.VISIBLE);
        mButtonSearch.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<Genre> genres) {
        mListGenres = genres;
        OptionGenresAdapter optionGenresAdapter = new OptionGenresAdapter(mListGenres);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mGenresActivity, SPAN_COUNT);
        mGenres.setLayoutManager(layoutManager);
        mGenres.setAdapter(optionGenresAdapter);
    }

    @Override
    public void onFailed(Exception exception) {

    }

    @Override
    public void onDismissLoading() {
        mLinearLoading.setVisibility(View.GONE);
        mButtonSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        StringBuilder strGenres = new StringBuilder();
        for (Genre genre : mListGenres) {
            if (genre.isStateGenres()) {
                strGenres.append(genre.getId());
                strGenres.append(getString(R.string.comma));
            }
        }

        if (strGenres.length() > 0) {
            mGenresActivity.replaceFragment(ResultGenresFragment.newInstance(
                    strGenres.toString().substring(0, strGenres.toString().length()
                            - LAST_ELEMENT)));
        } else {
            Toast.makeText(mGenresActivity, getString(R.string.please_select_genre), Toast.LENGTH_SHORT).show();
        }
    }
}
