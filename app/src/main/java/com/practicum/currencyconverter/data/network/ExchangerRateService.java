package com.practicum.currencyconverter.data.network;

import com.practicum.currencyconverter.data.models.CurrencyRate;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExchangerRateService {

    @GET("daily_json.js")
    Call<CurrencyRate> getCurrency();
}
