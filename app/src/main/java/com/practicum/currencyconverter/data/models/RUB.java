package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class RUB extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_russia;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_russia;
    }

    @Override
    public String getCode() {
        return "RUB";
    }

    @Override
    public double getValue() {
        return 1;
    }

    @Override
    public double getPrevious() {
        return 1;
    }

    @Override
    public int getNominal() {
        return 1;
    }
}
