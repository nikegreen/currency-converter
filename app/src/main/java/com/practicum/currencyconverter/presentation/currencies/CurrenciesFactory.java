package com.practicum.currencyconverter.presentation.currencies;

import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.data.models.CurrencyRate;
import com.practicum.currencyconverter.data.models.RUB;
import com.practicum.currencyconverter.data.models.Valute;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class CurrenciesFactory {

    public static List<Currency> getAvailableCurrencies(final CurrencyRate data) {
        final List<Currency> currencies = new ArrayList<>();
        final Valute valute = data.getValute();

        currencies.add(new RUB());
        addIfNotNull(valute.AUD(), currencies);
        addIfNotNull(valute.CAD(), currencies);
        addIfNotNull(valute.CHF(), currencies);
        addIfNotNull(valute.CNY(), currencies);
        addIfNotNull(valute.CZK(), currencies);
        addIfNotNull(valute.DKK(), currencies);
        addIfNotNull(valute.EUR(), currencies);
        addIfNotNull(valute.GBP(), currencies);
        addIfNotNull(valute.HKD(), currencies);
        addIfNotNull(valute.JPY(), currencies);
        addIfNotNull(valute.NOK(), currencies);
        addIfNotNull(valute.PLN(), currencies);
        addIfNotNull(valute.SEK(), currencies);
        addIfNotNull(valute.SGD(), currencies);
        addIfNotNull(valute.TRY(), currencies);
        addIfNotNull(valute.USD(), currencies);
        addIfNotNull(valute.ZAR(), currencies);

        return currencies;
    }

    private static void addIfNotNull(@Nullable final Currency currency, final List<Currency> currencies) {
        if (currencies != null) {
            currencies.add(currency);
        }
    }

}
