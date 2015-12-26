package com.sapibagus.android.view.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.api.model.response.PageResponse;
import com.sapibagus.android.view.detail.widget.WebLoadingView;
import com.sapibagus.android.view.page.PageView;
import com.sapibagus.android.view.page.presenter.PagePresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PageFragment extends BaseFragment implements PageView {

    public static final String ARG_PAGE_NAME = "arg_page_name";

    @Bind(R.id.web_view) WebLoadingView webView;

    @Inject
    PagePresenter presenter;

    public static PageFragment newInstance(String pageName) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE_NAME, pageName);

        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.INSTANCE.getContextComponent().inject(this);
        presenter.init(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadPage(getArguments().getString(ARG_PAGE_NAME));
    }

    @Override
    public void initToolbar() {
        // Do nothing
    }

    @Override
    public void showPageContent(PageResponse pageResponse) {
        webView.bind(pageResponse.page.title, pageResponse.page.content);
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {
        // Do nothing
    }

    @Override
    public void onLoading(boolean status) {
        webView.onLoading(status);
    }
}
