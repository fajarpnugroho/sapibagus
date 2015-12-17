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
import com.sapibagus.android.analytic.AnalyticManager;
import com.sapibagus.android.analytic.AnalyticTracker;
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

public class MainActivity extends BaseActivity implements MainView, AnalyticTracker {

    public static final int HOME_POSITION = 0;
    public static final int BELAJAR_POSITION = 1;
    public static final int INFO_POSITION = 2;
    public static final int EVENT_POSITION = 3;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.sliding_tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionsMenu fabMenu;

    @Inject MainPresenter presenter;
    @Inject AnalyticManager analyticManager;

    private CategoriesResponse categoriesResponse;

    private View.OnClickListener fabPelatihanClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            trackEvent("FAB", "Open Pelatihan", "Pelatihan");

            presenter.openPage(getString(R.string.slug_page_pelatihan));

            fabMenu.collapse();
        }
    };

    private View.OnClickListener fabMitraClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.openPage(getString(R.string.slug_page_mitra_binaan));

            trackEvent("FAB", "Open Mitra", "Mitra");

            fabMenu.collapse();
        }
    };

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
            pagerAdapter.addFragment(PostsFragment.newInstance(categoryEntity.slug),
                    categoryEntity.title);
        }

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("BELAJAR"));
        tabLayout.addTab(tabLayout.newTab().setText("INFO"));
        tabLayout.addTab(tabLayout.newTab().setText("EVENT"));

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

    @Override
    public void initFAB() {
        fabMenu.addButton(generateFAB(fabPelatihanClickListener, "Pelatihan",
                R.drawable.ic_local_library_white_24dp));
        fabMenu.addButton(generateFAB(fabMitraClickListener, "Mitra",
                R.drawable.ic_business_center_white_24dp));
    }

    private FloatingActionButton generateFAB(View.OnClickListener clickListener,
                                             String title, int icon) {
        FloatingActionButton fab = new FloatingActionButton(this);
        fab.setTitle(title);
        fab.setSize(FloatingActionButton.SIZE_MINI);
        fab.setOnClickListener(clickListener);
        fab.setColorNormal(R.color.floating_action_button);
        fab.setColorPressed(R.color.floating_action_button_press);
        fab.setIcon(icon);
        return fab;
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
