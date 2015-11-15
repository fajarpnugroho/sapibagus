package com.sapibagus.android.view.home.presenter;

import android.app.Application;

import com.google.gson.Gson;
import com.sapibagus.android.api.model.response.CategoriesResponse;
import com.sapibagus.android.utils.AssetUtils;
import com.sapibagus.android.view.home.MainView;

import java.io.IOException;
import java.io.Reader;

import javax.inject.Inject;

import timber.log.Timber;

public class MainPresenter {

    public static final String UNEXPECTED_ERROR = "Unexpected error";
    public static final String READER_ALREADY_BEEN_CLOSED = "Reader already been closed";

    private MainView view;
    private Application application;

    @Inject
    public MainPresenter(Application application) {
        this.application = application;
    }

    public void initView(MainView view) {
        this.view = view;
    }

    public void getCategories() {
        Reader reader = null;
        try {
            reader = AssetUtils.readFile(application, "category.json");
            CategoriesResponse response =
                    new Gson().fromJson(
                            reader,
                            CategoriesResponse.class);
            view.showCategories(response);

        } catch (IOException e) {
            throw new RuntimeException(UNEXPECTED_ERROR);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Timber.e(READER_ALREADY_BEEN_CLOSED);
            }
        }
    }
}
