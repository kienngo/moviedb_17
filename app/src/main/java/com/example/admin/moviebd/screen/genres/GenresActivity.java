package com.example.admin.moviebd.screen.genres;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.screen.BaseActivity;
import com.example.admin.moviebd.screen.genres.option.OptionGenresFragment;
import com.example.admin.moviebd.screen.genres.result.ResultGenresFragment;

public class GenresActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolbarGenres;
    private ImageView mImageBack;
    private TextView mTextTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        initView();
        setOnClickListener();
        setupToolbar();
        replaceFragment(OptionGenresFragment.newInstance());
    }

    private void initView() {
        mToolbarGenres = findViewById(R.id.toolbar_genres);
        mImageBack = findViewById(R.id.image_back);
        mTextTitle = findViewById(R.id.text_title);
    }

    private void setOnClickListener() {
        mImageBack.setOnClickListener(this);
    }

    private void setupToolbar() {
        mTextTitle.setText(getString(R.string.toolbar_movie_genres_title));
        setSupportActionBar(mToolbarGenres);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_genres, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        checkStateCurrentFragment();
    }

    private void checkStateCurrentFragment() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.layout_genres);
        if (f instanceof OptionGenresFragment) {
            finish();
        } else if (f instanceof ResultGenresFragment) {
            replaceFragment(OptionGenresFragment.newInstance());
        }
    }
}
