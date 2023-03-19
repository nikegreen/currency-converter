package com.practicum.currencyconverter.data.cache;

import com.practicum.currencyconverter.data.models.CurrencyRate;
import com.practicum.currencyconverter.data.network.ExchangerRateService;
import com.practicum.currencyconverter.data.network.ResultCallback;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyCourseDataStore {

    private final ExchangerRateService exchangerRateService;

    private CurrencyRate currencyRate;

    public CurrencyCourseDataStore(final ExchangerRateService exchangerRateService) {
        this.exchangerRateService = exchangerRateService;
    }

    public void getCurrencyResult(final boolean forceUpdate, final ResultCallback<CurrencyRate> resultCallback) {
        if (forceUpdate || currencyRate == null) {
            exchangerRateService.getCurrency().enqueue(new Callback<CurrencyRate>() {
                @Override
                public void onResponse(@NonNull final Call<CurrencyRate> call, @NonNull final Response<CurrencyRate> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        currencyRate = response.body();
                        resultCallback.onSuccess(response.body());
                    } else {
                        resultCallback.onFailure(new IllegalArgumentException("Server exception"));
                    }
                }

                @Override
                public void onFailure(@NonNull final Call<CurrencyRate> call, @NonNull final Throwable t) {
                    resultCallback.onFailure(t);
                }
            });
        } else {
            resultCallback.onSuccess(currencyRate);
        }
    }
}
