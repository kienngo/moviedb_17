package com.example.admin.moviebd.screen.search.option;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.SearchParameter;
import com.example.admin.moviebd.screen.BaseFragment;
import com.example.admin.moviebd.screen.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.moviebd.screen.search.QueryString.SORT_BY;
import static com.example.admin.moviebd.screen.search.QueryString.YEAR;

public class OptionSearchFragment extends BaseFragment implements OptionSearchAdapter.OnSearchItemClickListener {
    private static final int SPAN_COUNT = 4;
    private RecyclerView mReturnOptions;
    private SearchActivity mSearchActivity;
    private List<SearchParameter> mSearchParameters;

    public static OptionSearchFragment newInstance() {
        OptionSearchFragment optionSearchFragment = new OptionSearchFragment();
        return optionSearchFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchActivity = (SearchActivity) getActivity();
        mSearchParameters = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option_search, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initDataOptionSearch();
        initView();
        showOptionSearch();
    }

    private void initView() {
        mReturnOptions = mSearchActivity.findViewById(R.id.recycler_return_options);
    }

    private void initDataOptionSearch() {
        String[] sortBy = mSearchActivity.getResources().getStringArray(R.array.sort_by);
        String[] years = mSearchActivity.getResources().getStringArray(R.array.year);
        for (String item : sortBy) {
            mSearchParameters.add(new SearchParameter(SORT_BY, item, null));
        }
        for (String item : years) {
            mSearchParameters.add(new SearchParameter(YEAR, item, null));
        }
    }

    private void showOptionSearch() {
        OptionSearchAdapter optionSearchAdapter = new OptionSearchAdapter(mSearchParameters, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mSearchActivity, SPAN_COUNT);
        mReturnOptions.setLayoutManager(layoutManager);
        mReturnOptions.setAdapter(optionSearchAdapter);
    }

    @Override
    public void onItemClick(SearchParameter searchParameter) {

    }
}
