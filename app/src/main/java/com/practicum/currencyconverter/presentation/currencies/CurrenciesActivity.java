package com.practicum.currencyconverter.presentation.currencies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.widget.EditText;

import com.practicum.currencyconverter.R;
import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.databinding.ActivityCurrenciesBinding;
import com.practicum.currencyconverter.presentation.base.BaseActivity;
import com.practicum.currencyconverter.presentation.converter.CurrencyInput;
import com.practicum.currencyconverter.ui.decorators.HorizontalLineItemDecorator;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

public class CurrenciesActivity extends BaseActivity<CurrenciesViewModel> implements CurrenciesAdapter.OnItemClickListener {

    private static final String ARG_CURRENCY = "arg_currency";
    private static final String ARG_CURRENCY_INPUT = "arg_currency_input";

    private final CurrenciesAdapter currenciesAdapter = new CurrenciesAdapter(this);

    private ActivityCurrenciesBinding binding;

    public static ActivityResultContract<CurrencyInput, Pair<Currency, CurrencyInput>> getContract(final Context packageContext) {
        return new ActivityResultContract<CurrencyInput, Pair<Currency, CurrencyInput>>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull final Context context, final CurrencyInput input) {
                final Intent intent = new Intent(packageContext, CurrenciesActivity.class);
                intent.putExtra(ARG_CURRENCY_INPUT, input);
                return intent;
            }

            @Override
            public Pair<Currency, CurrencyInput> parseResult(final int resultCode, @Nullable final Intent intent) {
                if (resultCode == RESULT_OK && intent != null) {
                    final Currency currency = (Currency) intent.getExtras().get(ARG_CURRENCY);
                    final CurrencyInput currencyInput = (CurrencyInput) intent.getExtras().get(ARG_CURRENCY_INPUT);

                    return Pair.create(currency, currencyInput);
                }

                return null;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrenciesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        initViewModel();

        viewModel.loadCurrencies();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected CurrenciesViewModel createViewModel() {
        return new ViewModelProvider(this).get(CurrenciesViewModel.class);
    }

    @Override
    public void onItemClick(final Currency currency) {
        final CurrencyInput currencyInput = (CurrencyInput) getIntent().getExtras().get(ARG_CURRENCY_INPUT);
        final Intent intent = new Intent();

        intent.putExtra(ARG_CURRENCY, currency);
        intent.putExtra(ARG_CURRENCY_INPUT, currencyInput);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void initViews() {
        initToolbar();
        initSearchView();
        initRecycler();
    }

    private void initToolbar() {
        binding.toolbar.setTitle(R.string.currencies_title);
        setSupportActionBar(binding.toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
    }

    private void initSearchView() {
        final EditText editText = binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setTextAppearance(R.style.Text_Bold_16_20_Black);

        binding.searchView.setOnClickListener(v -> binding.searchView.setIconified(false));
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                viewModel.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                viewModel.search(newText);
                return true;
            }
        });
    }

    private void initRecycler() {
        binding.currenciesRecyclerView.setAdapter(currenciesAdapter);

        final HorizontalLineItemDecorator itemDecoration = new HorizontalLineItemDecorator();
        itemDecoration.setColor(getResources().getColor(R.color.text_gray, null));
        itemDecoration.setLeftPadding((int) getResources().getDimension(R.dimen.currency_decorator_padding));

        binding.currenciesRecyclerView.addItemDecoration(itemDecoration);
    }

    private void initViewModel() {
        viewModel.getCurrenciesLiveData().observe(this, currenciesAdapter::update);
    }
}