package com.practicum.currencyconverter.data.network;

public class ExchangerRateUrlProvider {

    private static final String BASE_URL = "https://www.cbr-xml-daily.ru/";

    public String provideBaseUrl() {
        return BASE_URL;
    }
}
