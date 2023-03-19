package com.practicum.currencyconverter.presentation.currencies;

import android.util.Log;

import com.practicum.currencyconverter.app.ResourceProvider;
import com.practicum.currencyconverter.data.cache.CurrencyCourseDataStore;
import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.data.models.CurrencyRate;
import com.practicum.currencyconverter.data.network.ResultCallback;
import com.practicum.currencyconverter.di.DI;
import com.practicum.currencyconverter.presentation.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CurrenciesViewModel extends BaseViewModel {

    private final CurrencyCourseDataStore currencyCourseDataStore = DI.currencyCourseDataStore();
    private final ResourceProvider resourceProvider = DI.resourceProvider();

    private final MutableLiveData<List<Currency>> currenciesLiveData = new MutableLiveData<>();
    private List<Currency> currencies = new ArrayList<>();

    public LiveData<List<Currency>> getCurrenciesLiveData() {
        return currenciesLiveData;
    }

    public void loadCurrencies() {
        currencyCourseDataStore.getCurrencyResult(false, new ResultCallback<CurrencyRate>() {
            @Override
            public void onSuccess(final CurrencyRate data) {
                currencies = CurrenciesFactory.getAvailableCurrencies(data);
                currenciesLiveData.postValue(currencies);
            }

            @Override
            public void onFailure(final Throwable error) {
                Log.d("CurrenciesViewModel", error.getMessage());
            }
        });

    }

    public void search(final String query) {
        // кажется, это последняя пакость наших троллей ¯\_(ツ)_/¯
        // давайте же наконец сделаем наше приложение полезным для любимых пользователей
        if (query.isEmpty()) {
            showWholeData();
        } else {
            showFilteredData(query);
        }
    }

    private void showWholeData() {
        currenciesLiveData.postValue(currencies);
    }

    private void showFilteredData(final String query) {
        final List<Currency> searchResult = currencies.stream()
                .filter(currency -> {
                            if (currency.getCode().toLowerCase().contains(query.toLowerCase())) {
                                return true;
                            }
                            return resourceProvider.getString(currency.nameRes()).toLowerCase().contains(query.toLowerCase());
                        }
                ).collect(Collectors.toList());
        currenciesLiveData.postValue(searchResult);
    }
}
