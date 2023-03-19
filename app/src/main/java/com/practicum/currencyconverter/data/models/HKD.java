package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class HKD extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_hong_kong;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_hong_kong;
    }

    @Override
    public String getCode() {
        return "HKD";
    }
}