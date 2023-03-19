package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class SEK extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_sweden;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_sweden;
    }

    @Override
    public String getCode() {
        return "SEK";
    }
}