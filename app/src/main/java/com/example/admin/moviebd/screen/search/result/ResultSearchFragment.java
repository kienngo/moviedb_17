package com.example.admin.moviebd.screen.search.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.SearchResult;
import com.example.admin.moviebd.screen.BaseFragment;
import com.example.admin.moviebd.screen.search.SearchActivity;
import com.example.admin.moviebd.utils.Constants;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.util.List;

import static com.example.admin.moviebd.screen.movie.PageDefault.PAGE_DEFAULT;
import static com.example.admin.moviebd.screen.search.QueryString.KEY_PASSED_DATA;
import static com.example.admin.moviebd.screen.search.QueryString.KEY_SEARCH_CONDITION;
import static com.example.admin.moviebd.screen.search.SearchCondition.MULTI_SEARCH;
import static com.example.admin.moviebd.screen.search.SearchCondition.SEARCH_MOVIE;
import static com.example.admin.moviebd.screen.search.SearchCondition.SEARCH_TELEVISION;

public class ResultSearchFragment extends BaseFragment implements ResultSearchContact.View {
    private RecyclerView mSearchResults;
    private LinearLayout mDialogLoading;
    private ResultSearchPresenter mResultSearchPresenter;
    private SearchActivity mSearchActivity;
    private String mTextSearch;
    private int mSearchCondition;

    public static ResultSearchFragment newInstance(String textSearch, int searchCondition) {
        ResultSearchFragment resultSearchFragment = new ResultSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PASSED_DATA, textSearch);
        bundle.putInt(KEY_SEARCH_CONDITION, searchCondition);
        resultSearchFragment.setArguments(bundle);
        return resultSearchFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTextSearch = bundle.getString(KEY_PASSED_DATA);
            mSearchCondition = bundle.getInt(KEY_SEARCH_CONDITION);
        }
        mResultSearchPresenter = new ResultSearchPresenter(this,
                Injection.getInstance().getSearchResultRepository());
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
        searchData();
    }

    private void initView() {
        mSearchResults = mSearchActivity.findViewById(R.id.recycler_search_result);
        mDialogLoading = mSearchActivity.findViewById(R.id.dialog_loading);
    }

    private void searchData() {
        switch (mSearchCondition) {
            case SEARCH_MOVIE:
                // TODO: 8/5/2018 search by movie
                mResultSearchPresenter.getDataSearchFromUrl(StringUtils.formatStringSearchUrl(Constants.FINAL_API_SEARCH,
                        Constants.ApiAddContent.SEARCH_MOVIE, mTextSearch, PAGE_DEFAULT));
                break;
            case SEARCH_TELEVISION:
                // TODO: 8/5/2018 search by television
                mResultSearchPresenter.getDataSearchFromUrl(StringUtils.formatStringSearchUrl(Constants.FINAL_API_SEARCH,
                        Constants.ApiAddContent.SEARCH_TELEVISION, mTextSearch, PAGE_DEFAULT));
                break;
            case MULTI_SEARCH:
                // TODO: 8/5/2018 search by all
                mResultSearchPresenter.getDataSearchFromUrl(StringUtils.formatStringSearchUrl(Constants.FINAL_API_SEARCH,
                        Constants.ApiAddContent.SEARCH_MULTI, mTextSearch, PAGE_DEFAULT));
                break;
        }
    }

    @Override
    public void onStartLoading() {
        mDialogLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<SearchResult> searchResults) {
        ResultSearchAdapter resultSearchAdapter = new ResultSearchAdapter(mSearchActivity, searchResults, mSearchCondition);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mSearchActivity, LinearLayoutManager.VERTICAL, false);
        mSearchResults.setLayoutManager(layoutManager);
        mSearchResults.setAdapter(resultSearchAdapter);
    }

    @Override
    public void onFailed(Exception exception) {
        // TODO: 8/6/2018 show error
    }

    @Override
    public void onDismissLoading() {
        mDialogLoading.setVisibility(View.GONE);
    }
}
