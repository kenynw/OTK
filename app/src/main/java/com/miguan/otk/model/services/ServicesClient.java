package com.miguan.otk.model.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class ServicesClient {

    private static Services mInstance;

    private static OkHttpClient okHttpClient;

    public static Services getServices () {
        if (mInstance == null) mInstance = createRetrofit().create(Services.class);
        return mInstance;
    }

    private static OkHttpClient createClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Services.BASE_URL)
                .addConverterFactory(WrapperConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createClient())
                .build();
    }

}
