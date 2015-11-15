package com.sapibagus.android.api;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sapibagus.android.api.service.DetailServices;
import com.sapibagus.android.api.service.PageServices;
import com.sapibagus.android.api.service.StreamServices;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor());
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiConfig.API_ENDPOINT)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    StreamServices provideStreamServices(Retrofit retrofit) {
        return retrofit.create(StreamServices.class);
    }

    @Provides
    @Singleton
    DetailServices provideDetailServices(Retrofit retrofit) {
        return retrofit.create(DetailServices.class);
    }

    @Provides
    @Singleton
    PageServices providePageServices(Retrofit retrofit) {
        return retrofit.create(PageServices.class);
    }
}
