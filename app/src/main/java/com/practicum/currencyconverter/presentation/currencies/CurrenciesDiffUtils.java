package com.practicum.currencyconverter.presentation.currencies;

import com.practicum.currencyconverter.data.models.Currency;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class CurrenciesDiffUtils extends DiffUtil.ItemCallback<Currency> {

    @Override
    public boolean areItemsTheSame(@NonNull final Currency oldItem, @NonNull final Currency newItem) {
        return oldItem.getCode().equals(newItem.getCode());
    }

    @Override
    public boolean areContentsTheSame(@NonNull final Currency oldItem, @NonNull final Currency newItem) {
        return oldItem.equals(newItem);
    }
}
