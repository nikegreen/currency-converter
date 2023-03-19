package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class CAD extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_canada;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_canada;
    }

    @Override
    public String getCode() {
        return "CAD";
    }
}