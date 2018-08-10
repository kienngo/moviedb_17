package com.example.admin.moviebd.screen.search;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.screen.BaseActivity;
import com.example.admin.moviebd.screen.search.option.OptionSearchFragment;
import com.example.admin.moviebd.screen.search.result.ResultSearchFragment;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.admin.moviebd.utils.Constant.ApiAddContent.SEARCH_MOVIE;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.SEARCH_MULTI;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.SEARCH_TELEVISION;


public class SearchActivity extends BaseActivity implements TextView.OnEditorActionListener,
        View.OnClickListener {
    private static final int REQ_CODE_SPEECH_INPUT = 12;
    private Toolbar mToolbarSearch;
    private EditText mEditSearch;
    private String mSearchCondition = SEARCH_MULTI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        setupToolbar();
        setOnClickListener();
        replaceFragment(OptionSearchFragment.newInstance());
    }

    private void initView() {
        mToolbarSearch = findViewById(R.id.toolbar_search);
        mEditSearch = findViewById(R.id.edittext_search);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbarSearch);
        // TODO: 8/4/2018 set up icon back for toolbar
        mToolbarSearch.setNavigationIcon(R.drawable.ic_back);
        mToolbarSearch.setNavigationOnClickListener(this);
    }

    private void setOnClickListener() {
        mEditSearch.setOnEditorActionListener(this);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_search, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // TODO: 8/5/2018 Check fragment current when back
    private void checkStateCurrentFragment() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.layout_search);
        if (f instanceof OptionSearchFragment) {
            finish();
        } else if (f instanceof ResultSearchFragment) {
            replaceFragment(OptionSearchFragment.newInstance());
        }
    }

    // TODO: 8/6/2018 speak voice google
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            a.getMessage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && null != data) {
            switch (requestCode) { // TODO: 8/6/2018 result request speak voice
                case REQ_CODE_SPEECH_INPUT:
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (!result.isEmpty()) {
                        if (!TextUtils.isEmpty(result.get(0))) {
                            mEditSearch.setText(result.get(0));
                            hideSoftInputFromWindow(mEditSearch);
                            replaceFragment(ResultSearchFragment.newInstance(result.get(0), mSearchCondition));
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_movie:
                mSearchCondition = SEARCH_MOVIE;
                mEditSearch.setHint(getString(R.string.search_movie));
                break;
            case R.id.action_search_television:
                mSearchCondition = SEARCH_TELEVISION;
                mEditSearch.setHint(getString(R.string.search_television));
                break;
            case R.id.action_multi_search:
                mSearchCondition = SEARCH_MULTI;
                mEditSearch.setHint(getString(R.string.input_keyword));
                break;
            case R.id.action_voice:
                promptSpeechInput();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                hideSoftInputFromWindow(mEditSearch);
                replaceFragment(ResultSearchFragment.newInstance(mEditSearch.getText().toString(), mSearchCondition));
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        checkStateCurrentFragment();
    }

    @Override
    public void onBackPressed() {
        checkStateCurrentFragment();
    }
}
