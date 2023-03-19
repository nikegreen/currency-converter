package com.practicum.currencyconverter.data.network;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private final static int TIMEOUT_IN_SECOND = 10;
    private static Retrofit retrofit;

    private final ExchangerRateUrlProvider urlProvider;

    public ApiProvider(ExchangerRateUrlProvider urlProvider) {
        this.urlProvider = urlProvider;
    }

    public ExchangerRateService getExchangerRateService() {
        return getRetrofit().create(ExchangerRateService.class);
    }

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = buildRetrofit();
        }

        return retrofit;
    }

    @NonNull
    private Retrofit buildRetrofit() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(urlProvider.provideBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
