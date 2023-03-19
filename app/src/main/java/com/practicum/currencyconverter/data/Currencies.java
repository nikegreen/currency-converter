package com.practicum.currencyconverter.data;

import com.practicum.currencyconverter.data.models.AUD;
import com.practicum.currencyconverter.data.models.CAD;
import com.practicum.currencyconverter.data.models.CHF;
import com.practicum.currencyconverter.data.models.CNY;
import com.practicum.currencyconverter.data.models.CZK;
import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.data.models.CurrencyRate;
import com.practicum.currencyconverter.data.models.DKK;
import com.practicum.currencyconverter.data.models.EUR;
import com.practicum.currencyconverter.data.models.GBP;
import com.practicum.currencyconverter.data.models.HKD;
import com.practicum.currencyconverter.data.models.JPY;
import com.practicum.currencyconverter.data.models.NOK;
import com.practicum.currencyconverter.data.models.PLN;
import com.practicum.currencyconverter.data.models.RUB;
import com.practicum.currencyconverter.data.models.SEK;
import com.practicum.currencyconverter.data.models.SGD;
import com.practicum.currencyconverter.data.models.TRY;
import com.practicum.currencyconverter.data.models.USD;
import com.practicum.currencyconverter.data.models.ZAR;

public class Currencies {

    public static Currency getByClass(final Currency currency, final CurrencyRate currencyRate) {
        if (currency instanceof AUD) {
            return currencyRate.getValute().AUD();
        } else if (currency instanceof CAD) {
            return currencyRate.getValute().CAD();
        } else if (currency instanceof CHF) {
            return currencyRate.getValute().CHF();
        } else if (currency instanceof CNY) {
            return currencyRate.getValute().CNY();
        } else if (currency instanceof CZK) {
            return currencyRate.getValute().CZK();
        } else if (currency instanceof DKK) {
            return currencyRate.getValute().DKK();
        } else if (currency instanceof EUR) {
            return currencyRate.getValute().EUR();
        } else if (currency instanceof GBP) {
            return currencyRate.getValute().GBP();
        } else if (currency instanceof HKD) {
            return currencyRate.getValute().HKD();
        } else if (currency instanceof JPY) {
            return currencyRate.getValute().JPY();
        } else if (currency instanceof NOK) {
            return currencyRate.getValute().NOK();
        } else if (currency instanceof PLN) {
            return currencyRate.getValute().PLN();
        } else if (currency instanceof RUB) {
            return new RUB();
        } else if (currency instanceof SEK) {
            return currencyRate.getValute().SEK();
        } else if (currency instanceof SGD) {
            return currencyRate.getValute().SGD();
        } else if (currency instanceof TRY) {
            return currencyRate.getValute().TRY();
        } else if (currency instanceof USD) {
            return currencyRate.getValute().USD();
        } else if (currency instanceof ZAR) {
            return currencyRate.getValute().ZAR();
        } else {
            throw new IllegalArgumentException("This type of currency unhandled");
        }
    }
}
