package com.practicum.currencyconverter.data;

import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.data.models.CurrencyRate;
import com.practicum.currencyconverter.data.models.RUB;

public class CurrenciesConverter {

    public double calculateCurrent(final Currency from, final Currency to, final CurrencyRate data) {
        final Currency fromResult = Currencies.getByClass(from, data);
        final Currency toResult = Currencies.getByClass(to, data);

        if (from.getClass().equals(to.getClass())) {
            return 1;
        } else if (from instanceof RUB) {
            return fromResult.getNominal() / toResult.getValue();
        } else if (to instanceof RUB) {
            return fromResult.getValue() / toResult.getNominal();
        } else {
            return fromResult.getValue() * fromResult.getNominal() / toResult.getValue() * toResult.getNominal();
        }
    }

    public double calculatePrevious(final Currency from, final Currency to, final CurrencyRate data) {
        final Currency fromResult = Currencies.getByClass(from, data);
        final Currency toResult = Currencies.getByClass(to, data);

        if (from.getClass().equals(to.getClass())) {
            return 1;
        } else if (from instanceof RUB) {
            return fromResult.getNominal() / toResult.getPrevious();
        } else if (to instanceof RUB) {
            return fromResult.getPrevious() / toResult.getNominal();
        } else {
            return fromResult.getPrevious() * fromResult.getNominal() / toResult.getPrevious() * toResult.getNominal();
        }
    }
}
