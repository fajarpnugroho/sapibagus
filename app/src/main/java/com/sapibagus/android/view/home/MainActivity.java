package com.sapibagus.android.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.CategoryEntity;
import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.api.model.response.CategoriesResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.detail.DetailActivity;
import com.sapibagus.android.view.home.fragment.PostsFragment;
import com.sapibagus.android.view.home.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, PostsFragment.Controller {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.sliding_tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionButton fab;

    @Inject MainPresenter presenter;

    private CategoriesResponse categoriesResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.INSTANCE.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initFAB();

        presenter.initView(this);
        presenter.getCategories();
    }


    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void initPager() {
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        for (CategoryEntity categoryEntity : categoriesResponse.data) {
            pagerAdapter.addFragment(PostsFragment.newInstance(categoryEntity.slug),
                    categoryEntity.title);
        }

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initFAB() {
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void showCategories(CategoriesResponse response) {
        this.categoriesResponse = response;
        initPager();
    }

    @Override
    public void navigateDetail(PostEntity postEntity) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POST_ID, (int) postEntity.id);
        startActivity(intent);
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
