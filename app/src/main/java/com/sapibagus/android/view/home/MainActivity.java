package com.sapibagus.android.view.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.analytic.AnalyticManager;
import com.sapibagus.android.analytic.AnalyticTracker;
import com.sapibagus.android.api.model.entity.CategoryEntity;
import com.sapibagus.android.api.model.response.CategoriesResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.home.fragment.PageFragment;
import com.sapibagus.android.view.home.fragment.PostsFragment;
import com.sapibagus.android.view.home.presenter.MainNavigator;
import com.sapibagus.android.view.home.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, AnalyticTracker {

    public static final int HOME_POSITION = 0;
    public static final int BELAJAR_POSITION = 1;
    public static final int INFO_POSITION = 2;
    public static final int BISNIS_POSITION = 3;
    public static final int PELATIHAN_POSITION = 4;
    public static final int EVENT_POSITION = 5;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.sliding_tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionButton fabButton;

    @Inject MainPresenter presenter;
    @Inject AnalyticManager analyticManager;

    private CategoriesResponse categoriesResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.INSTANCE.getContextComponent().inject(this);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initFAB();

        presenter.init(this, new MainNavigator(this));
        presenter.getCategories();
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null) {
            throw new IllegalStateException("Acitivty must provide action bar");
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void initPager() {
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        for (CategoryEntity categoryEntity : categoriesResponse.data) {
            if (categoryEntity.visible == 1) {
                pagerAdapter.addFragment(generateFragment(categoryEntity), categoryEntity.title);
            }
        }

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(pagerAdapter);

        for (int i = 0; i <  pagerAdapter.getCount(); i++ ) {
            tabLayout.addTab(tabLayout.newTab().setText(pagerAdapter.getPageTitle(i)));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case HOME_POSITION:
                        trackScreen("Home");
                        break;
                    case BELAJAR_POSITION:
                        trackScreen("Belajar");
                        break;
                    case INFO_POSITION:
                        trackScreen("Info");
                        break;
                    case BISNIS_POSITION:
                        trackScreen("Bisnis");
                        break;
                    case PELATIHAN_POSITION:
                        trackScreen("Pelatihan");
                        break;
                    case EVENT_POSITION:
                        trackScreen("Event");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private Fragment generateFragment(CategoryEntity categoryEntity) {
        if (categoryEntity.category.equalsIgnoreCase(CategoryEntity.MENU_TYPE)) {
            return PostsFragment.newInstance(categoryEntity.slug);
        } else if (categoryEntity.category.equalsIgnoreCase(CategoryEntity.PAGE_TYPE)) {
            return PageFragment.newInstance(categoryEntity.slug);
        } else {
            throw new IllegalStateException("Invalid category type");
        }

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toko:
                presenter.openUrlToko();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void initFAB() {
        fabButton.setImageResource(R.drawable.ic_phone_white_24dp);
    }

    @OnClick(R.id.fab)
    public void onFABClick() {
        trackEvent("FAB", "Click Phone Dial", "Dial Number");
        presenter.openPhoneDial();
    }

    @Override
    public void showCategories(CategoriesResponse response) {
        this.categoriesResponse = response;
        initPager();
    }

    @Override
    public void trackScreen(String screenName) {
        analyticManager.sendScreen(screenName);
    }

    @Override
    public void trackEvent(String eventCategory, String eventAction, String eventLabel) {
        analyticManager.sendEvent(eventCategory, eventAction, eventLabel);
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> titles = new ArrayList<>();

        public MainPagerAdapter(FragmentManager fm) { super(fm); }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() { return titles.size(); }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            titles.add(title);
            fragments.add(fragment);
        }


    }
}
