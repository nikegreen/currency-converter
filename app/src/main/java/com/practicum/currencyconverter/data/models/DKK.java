package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class DKK extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_denmark;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_denmark;
    }

    @Override
    public String getCode() {
        return "DKK";
    }
}