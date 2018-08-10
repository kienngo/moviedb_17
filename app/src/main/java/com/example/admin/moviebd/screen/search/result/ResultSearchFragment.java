package com.example.admin.moviebd.screen.search.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.model.SearchResult;
import com.example.admin.moviebd.screen.BaseFragment;
import com.example.admin.moviebd.screen.RecyclerViewScrollListener;
import com.example.admin.moviebd.screen.search.SearchActivity;
import com.example.admin.moviebd.utils.Constant;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.moviebd.screen.movie.PageDefault.PAGE_DEFAULT;
import static com.example.admin.moviebd.screen.search.QueryString.KEY_PASSED_DATA;
import static com.example.admin.moviebd.screen.search.QueryString.KEY_SEARCH_CONDITION;

public class ResultSearchFragment extends BaseFragment implements ResultSearchContact.View,
        ResultSearchAdapter.OnResultSearchItemClickListener {
    private RecyclerView mSearchResults;
    private LinearLayout mDialogLoading;
    private ResultSearchPresenter mResultSearchPresenter;
    private List<SearchResult> mSearch = new ArrayList<>();
    private ResultSearchAdapter mResultSearchAdapter;
    private RecyclerViewScrollListener mRecyclerViewScrollListener;
    private LinearLayoutManager mLinearLayoutManager;
    private SearchActivity mSearchActivity;
    private String mTextSearch;
    private String mSearchCondition;
    private int mCurrentPage;

    public static ResultSearchFragment newInstance(String textSearch, String searchCondition) {
        ResultSearchFragment resultSearchFragment = new ResultSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PASSED_DATA, textSearch);
        bundle.putString(KEY_SEARCH_CONDITION, searchCondition);
        resultSearchFragment.setArguments(bundle);
        return resultSearchFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTextSearch = bundle.getString(KEY_PASSED_DATA);
            mSearchCondition = bundle.getString(KEY_SEARCH_CONDITION);
        }
        mResultSearchPresenter = new ResultSearchPresenter(this,
                Injection.getInstance(mSearchActivity).getSearchResultRepository());
        mSearchActivity = (SearchActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_search, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        setOnScrollRecyclerView();
        searchData(PAGE_DEFAULT);
    }

    private void initView() {
        mSearchResults = mSearchActivity.findViewById(R.id.recycler_search);
        mDialogLoading = mSearchActivity.findViewById(R.id.dialog_loading);
    }

    private void searchData(int pageNumber) {
        mResultSearchPresenter.getDataSearchFromUrl(
                StringUtils.formatStringSearchUrl(Constant.FINAL_API_SEARCH,
                mSearchCondition, mTextSearch, pageNumber));
    }

    private void setOnScrollRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(mSearchActivity,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerViewScrollListener = new RecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                mCurrentPage = page;
                searchData(mCurrentPage);
            }
        };
        mSearchResults.addOnScrollListener(mRecyclerViewScrollListener);
    }

    @Override
    public void onStartLoading() {
        mDialogLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<SearchResult> searchResults) {
        mSearch.addAll(searchResults);
        mResultSearchAdapter = new ResultSearchAdapter(mSearchActivity,
                mSearch, mSearchCondition, this, mResultSearchPresenter);
        mSearchResults.setLayoutManager(mLinearLayoutManager);
        mSearchResults.setAdapter(mResultSearchAdapter);
        mSearchResults.scrollToPosition((mCurrentPage - Constant.COUNT) * Constant.SIZE_START);
    }

    @Override
    public void onFailed(Exception exception) {
        // TODO: 8/6/2018 show error
    }

    @Override
    public void onDismissLoading() {
        mDialogLoading.setVisibility(View.GONE);
        mRecyclerViewScrollListener.resetStateLoading();
    }

    @Override
    public void onItemClick(SearchResult searchResult) {
        Movie movie = new Movie(searchResult.getId(), searchResult.getVoteAverage(),
                searchResult.getTitleMovie(), searchResult.getPostPath(),
                searchResult.getReleaseDate());
        StringUtils.checkInsertMovie(mSearchActivity, mResultSearchPresenter.
                insertMovieLocal(movie));
        mResultSearchAdapter.notifyDataSetChanged();
    }
}
