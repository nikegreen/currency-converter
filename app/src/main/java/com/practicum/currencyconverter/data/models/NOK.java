package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class NOK extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_norway;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_norway;
    }

    @Override
    public String getCode() {
        return "NOK";
    }
}