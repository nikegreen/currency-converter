package com.practicum.currencyconverter.presentation.currencies;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.databinding.ItemCurrencyBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ViewHolder> {

    private final OnItemClickListener onItemClickListener;
    private final AsyncListDiffer<Currency> differ = new AsyncListDiffer<>(this, new CurrenciesDiffUtils());

    public CurrenciesAdapter(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ItemCurrencyBinding binding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(differ.getCurrentList().get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public void update(List<Currency> items) {
        differ.submitList(items);
    }

    interface OnItemClickListener {

        void onItemClick(final Currency currency);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemCurrencyBinding binding;

        ViewHolder(final ItemCurrencyBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(final Currency currency, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(currency));

            binding.iconImageView.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), currency.iconRes()));
            binding.nameTextView.setText(currency.nameRes());
            binding.codeTextView.setText(currency.getCode());
        }
    }
}
