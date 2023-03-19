package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class GBP extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_united_kingdom;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_great_britain;
    }

    @Override
    public String getCode() {
        return "GBP";
    }
}