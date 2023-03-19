package com.practicum.currencyconverter.presentation.converter;

import com.practicum.currencyconverter.data.Currencies;
import com.practicum.currencyconverter.data.CurrenciesConverter;
import com.practicum.currencyconverter.data.cache.CurrencyCourseDataStore;
import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.data.models.CurrencyRate;
import com.practicum.currencyconverter.data.network.ResultCallback;
import com.practicum.currencyconverter.di.DI;
import com.practicum.currencyconverter.presentation.base.BaseViewModel;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ConverterViewModel extends BaseViewModel {

    private final CurrencyCourseDataStore currencyCourseDataStore = DI.currencyCourseDataStore();
    private final CurrenciesConverter currenciesConverter = DI.currenciesConverter();
    private final CourseChangeMapper courseChangeMapper = DI.courseChangeMapper();

    private final MutableLiveData<ConverterState> converterStateLiveData = new MutableLiveData<>(ConverterState.DEFAULT);

    public LiveData<ConverterState> getConverterStateLiveData() {
        return converterStateLiveData;
    }

    public void getCurrencyRate(final boolean forceUpdate) {
        isLoadingLiveData.setValue(true);

        currencyCourseDataStore.getCurrencyResult(forceUpdate, new ResultCallback<CurrencyRate>() {
            @Override
            public void onSuccess(final CurrencyRate data) {
                final double currencyCourse = currenciesConverter.calculateCurrent(currentState().getFromCurrency(), currentState().getToCurrency(), data);
                final double previousCourse = currenciesConverter.calculatePrevious(currentState().getFromCurrency(), currentState().getToCurrency(), data);

                final Currency toCurrency = Currencies.getByClass(currentState().getToCurrency(), data);
                final Currency fromCurrency = Currencies.getByClass(currentState().getFromCurrency(), data);

                final ConverterState resultState = new ConverterState.Builder(currentState())
                        .setToCurrency(toCurrency)
                        .setFromCurrency(fromCurrency)
                        .setCurrencyCourse(currencyCourse)
                        .setCourseChangeVo(courseChangeMapper.map(currencyCourse, previousCourse))
                        .copy();

                converterStateLiveData.setValue(resultState);
                isLoadingLiveData.setValue(false);
                isErrorShownLiveData.setValue(false);
            }

            @Override
            public void onFailure(final Throwable error) {
                isLoadingLiveData.setValue(false);
                isErrorShownLiveData.setValue(true);
            }
        });
    }

    public void setFromCurrency(final Currency currency) {
        if (currency.getClass().equals(currentState().getFromCurrency().getClass())) {
            return;
        }
        final ConverterState resultState = new ConverterState.Builder(currentState())
                .setFromCurrency(currency)
                .setToCurrencyInput(0.0)
                .setFromCurrencyInput(0.0)
                .copy();

        converterStateLiveData.setValue(resultState);
        getCurrencyRate(false);
    }

    public void setToCurrency(final Currency currency) {
        if (currency.getClass().equals(currentState().getToCurrency().getClass())) {
            return;
        }
        final ConverterState resultState = new ConverterState.Builder(currentState())
                .setToCurrency(currency)
                .setToCurrencyInput(0.0)
                .setFromCurrencyInput(0.0)
                .copy();

        converterStateLiveData.setValue(resultState);
        getCurrencyRate(false);
    }

    public void swapCurrencies(final String fromCurrencyInput) {
        final double result = Double.parseDouble(fromCurrencyInput);
        swapCurrencies(result);
    }

    public void swapCurrencies(final double fromCurrencyInput) {
        final ConverterState resultState;

        if (currentState().getToCurrencyInput() == 0.0 && currentState().getFromCurrencyInput() == 0.0) {
            resultState = new ConverterState.Builder(currentState())
                    .setFromCurrency(currentState().getToCurrency())
                    .setToCurrency(currentState().getFromCurrency())
                    .copy();
        } else if (currentState().getToCurrencyInput() == 0.0) {
            resultState = new ConverterState.Builder(currentState())
                    .setFromCurrency(currentState().getToCurrency())
                    .setToCurrency(currentState().getFromCurrency())
                    .setToCurrencyInput(fromCurrencyInput)
                    .setFromCurrencyInput(0.0)
                    .copy();
        } else {
            resultState = new ConverterState.Builder(currentState())
                    .setFromCurrency(currentState().getToCurrency())
                    .setToCurrency(currentState().getFromCurrency())
                    .setToCurrencyInput(currentState().getFromCurrencyInput())
                    .setFromCurrencyInput(currentState().getToCurrencyInput())
                    .copy();
        }
        converterStateLiveData.setValue(resultState);
        getCurrencyRate(false);
    }

    @NonNull
    private ConverterState currentState() {
        return Objects.requireNonNull(converterStateLiveData.getValue());
    }

    public void convertUserInput(final String fromCurrencyInput) {
        final double result = Double.parseDouble(fromCurrencyInput);
        convertUserInput(result);
    }

    public void convertUserInput(final double fromCurrencyInput) {
        final double toCurrencyInput = getCurrencyCourse() * fromCurrencyInput;
        //<- вам не кажется, что здесь не должно быть 0?

        // кажется, это последняя пакость наших троллей ¯\_(ツ)_/¯
        // давайте же наконец сделаем наше приложение полезным для любимых пользователей
        // метод getCurrencyCourse() может нам помочь получить актуальный курс валют
        showActualRate(fromCurrencyInput, toCurrencyInput);
    }

    private void showActualRate(final double fromCurrencyInput, final double toCurrencyInput) {
        final ConverterState resultState = new ConverterState.Builder(currentState())
                .setFromCurrencyInput(fromCurrencyInput)
                .setToCurrencyInput(toCurrencyInput)
                .copy();

        converterStateLiveData.setValue(resultState);
    }

    private double getCurrencyCourse() {
        return currentState().getCurrencyCourse();
    }
}
