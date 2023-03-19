package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class CNY extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_china;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_china;
    }

    @Override
    public String getCode() {
        return "CNY";
    }
}