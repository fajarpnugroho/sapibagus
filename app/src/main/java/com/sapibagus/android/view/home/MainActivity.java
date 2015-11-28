package com.sapibagus.android.view.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.CategoryEntity;
import com.sapibagus.android.api.model.response.CategoriesResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.home.fragment.PostsFragment;
import com.sapibagus.android.view.home.presenter.MainNavigator;
import com.sapibagus.android.view.home.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.sliding_tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionsMenu fabMenu;

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

        presenter.init(this, new MainNavigator(this));
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
        FloatingActionButton fabPelatihan = new FloatingActionButton(this);
        fabPelatihan.setTitle("Pelatihan");
        fabPelatihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openPage(getString(R.string.slug_page_pelatihan));

                fabMenu.collapse();
            }
        });

        FloatingActionButton fabMitra = new FloatingActionButton(this);
        fabMitra.setTitle("Mitra");
        fabMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openPage(getString(R.string.slug_page_mitra_binaan));

                fabMenu.collapse();
            }
        });

        fabMenu.addButton(fabPelatihan);
        fabMenu.addButton(fabMitra);
    }

    @Override
    public void showCategories(CategoriesResponse response) {
        this.categoriesResponse = response;
        initPager();
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
