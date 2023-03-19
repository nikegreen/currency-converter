package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class CZK extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_czech_republic;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_czech_republic;
    }

    @Override
    public String getCode() {
        return "CZK";
    }
}