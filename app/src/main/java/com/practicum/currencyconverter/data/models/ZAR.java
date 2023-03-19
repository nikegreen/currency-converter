package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class ZAR extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_south_africa;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_south_africa;
    }

    @Override
    public String getCode() {
        return "ZAR";
    }
}