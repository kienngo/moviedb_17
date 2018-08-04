package com.example.admin.moviebd.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.screen.BaseActivity;
import com.example.admin.moviebd.screen.search.SearchActivity;

import static com.example.admin.moviebd.screen.main.TabType.MOVIE_FRAGMENT;
import static com.example.admin.moviebd.screen.main.TabType.TELEVISION_FRAGMENT;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener,
        View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mTextTitle;
    private ImageView mImageMenu;
    private MainPagerAdapter mMainPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setOnClickListener();
        setupToolbar();
        setupDrawerLayout();
        setupTabLayout();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.navigation_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mTextTitle = findViewById(R.id.textview_title);
        mImageMenu = findViewById(R.id.image_menu);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mTextTitle.setText(getString(R.string.movie));
    }

    private void setOnClickListener() {
        mImageMenu.setOnClickListener(this);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void setupDrawerLayout() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupTabLayout() {
        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mMainPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentSearch = new Intent(this, SearchActivity.class);
        startActivity(intentSearch);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case MOVIE_FRAGMENT:
                mTextTitle.setText(getString(R.string.movie));
                break;
            case TELEVISION_FRAGMENT:
                mTextTitle.setText(getString(R.string.television));
                break;
            default:
                mTextTitle.setText(R.string.movie);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_menu:
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_genres:
                break;
            case R.id.action_changes:
                break;
            case R.id.action_settings:
                break;
            case R.id.action_rate:
                break;
        }
        return true;
    }
}
