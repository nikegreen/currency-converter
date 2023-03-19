package com.practicum.currencyconverter.presentation.converter;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.practicum.currencyconverter.R;
import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.databinding.ActivityConverterBinding;
import com.practicum.currencyconverter.presentation.base.BaseActivity;
import com.practicum.currencyconverter.presentation.currencies.CurrenciesActivity;
import com.practicum.currencyconverter.ui.strings.NumberFormatter;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

public class ConverterActivity extends BaseActivity<ConverterViewModel> {

    private final ActivityResultLauncher<CurrencyInput> currencyScreenLauncher =
            registerForActivityResult(CurrenciesActivity.getContract(this), this::handleCurrencyResult);

    private ActivityConverterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initViewModel();
        openKeyBoard();
        viewModel.getCurrencyRate(true);
    }

    @Override
    protected ConverterViewModel createViewModel() {
        return new ViewModelProvider(this).get(ConverterViewModel.class);
    }

    @Override
    protected void showLoader(final boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.currencyCourseTextView.setVisibility(View.INVISIBLE);
            binding.currencyChangeTextView.setVisibility(View.INVISIBLE);
        } else {
            // кажется, злые тролли сломали наше приложение ༼ つ ◕_◕ ༽つ
            // нам срочно нужно написать здесь код, чтоб спасти пользователей от вечной загрузки и испорченных нервов
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.currencyCourseTextView.setVisibility(View.VISIBLE);
            binding.currencyChangeTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void handleError(final boolean isShown) {
        binding.fromInputEditText.setEnabled(!isShown);
        if (isShown) {
            binding.currencyCourseTextView.setText(R.string.converter_currency_cource_error);
            binding.currencyCourseTextView.setTextColor(ContextCompat.getColor(this, R.color.red));
            binding.fromBackground.setBackgroundColor(ContextCompat.getColor(this, R.color.red_10));
        } else {
            binding.currencyCourseTextView.setTextColor(ContextCompat.getColor(this, R.color.text_gray));
            binding.fromBackground.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void initView() {
        setFromInput();

        binding.fromCurrencyIconImageView.setOnClickListener(v -> openCurrencies(CurrencyInput.FROM));
        binding.toCurrencyIconImageView.setOnClickListener(v -> openCurrencies(CurrencyInput.TO));
        binding.refreshButton.setOnClickListener(v -> clearFields());
        binding.convertButton.setOnClickListener(v -> convertUserInput());
        binding.swapFab.setOnClickListener(v -> swapCurrencies());
    }

    private void setFromInput() {
        binding.fromInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                // nothing to do
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                // nothing to do
            }

            @Override
            public void afterTextChanged(final Editable s) {
                final boolean needShowConvertButton = s.length() > 0;
                // кажется, здесь тоже не хватает чего-то (°ロ°)
                binding.convertButton.setEnabled(needShowConvertButton);
            }
        });
    }

    private void initViewModel() {
        viewModel.getConverterStateLiveData().observe(this, this::render);
    }

    private void render(final ConverterState state) {
        setFromCurrency(state.getFromCurrency());
        setToCurrency(state.getToCurrency());
        setCurrencyCourse(state);
        setFromCurrencyInput(state);
        setToCurrencyInput(state);
        setCourseDifference(state.getCourseChangeVo());
    }

    private void openKeyBoard() {
        binding.fromInputEditText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void openCurrencies(final CurrencyInput currencyInput) {
        currencyScreenLauncher.launch(currencyInput);
    }

    private void handleCurrencyResult(@Nullable final Pair<Currency, CurrencyInput> currencyResult) {
        if (currencyResult == null) {
            return;
        }

        final CurrencyInput currencyInput = currencyResult.second;
        final Currency currency = currencyResult.first;

        switch (currencyInput) {
            case FROM:
                viewModel.setFromCurrency(currency);
                break;
            case TO:
                viewModel.setToCurrency(currency);
                break;
        }
    }

    private void setFromCurrency(final Currency currency) {
        binding.fromCurrencyIconImageView.setImageDrawable(ContextCompat.getDrawable(this, currency.iconRes()));
        binding.fromCurrencyCodeTextView.setText(currency.getCode());
    }

    private void setToCurrency(final Currency currency) {
        binding.toCurrencyIconImageView.setImageDrawable(ContextCompat.getDrawable(this, currency.iconRes()));
        binding.toCurrencyCodeTextView.setText(currency.getCode());
    }

    private void setCurrencyCourse(final ConverterState state) {
        if (state.getCurrencyCourse() != 0.0) {
            final String currencyCourseText = getString(
                    R.string.converter_currency_cource,
                    state.getFromCurrency().getNominal(),
                    state.getFromCurrency().getCode(),
                    state.getCurrencyCourse() * state.getFromCurrency().getNominal(),
                    state.getToCurrency().getCode()
            );
            binding.currencyCourseTextView.setText(currencyCourseText);
        } else {
            binding.currencyCourseTextView.setText("");
        }
    }

    private void setFromCurrencyInput(final ConverterState state) {
        final double fromCurrencyInput = state.getFromCurrencyInput();

        if (fromCurrencyInput == 0.0) {
            binding.fromInputEditText.setText("");
        } else {
            final String result = NumberFormatter.toString2Digit(fromCurrencyInput);
            binding.fromInputEditText.setText(result);
            binding.fromInputEditText.setSelection(result.length());
        }
    }

    private void setToCurrencyInput(final ConverterState state) {
        final double toCurrencyInput = state.getToCurrencyInput();

        if (toCurrencyInput == 0.0) {
            binding.toResultTextView.setText("");
        } else {
            binding.toResultTextView.setText(NumberFormatter.toString2Digit(toCurrencyInput));
        }
    }

    private void setCourseDifference(final CourseChangeVo courseChangeVo) {
        final char sign = courseChangeVo.isPositive() ? '+' : '-';
        final String text = getString(R.string.converter_course_change, sign, courseChangeVo.getDifferenceValue(), courseChangeVo.getDifferencePercent());

        final int color = courseChangeVo.isPositive() ? ContextCompat.getColor(this, R.color.green) : ContextCompat.getColor(this, R.color.red);
        final SpannableString spannable = new SpannableString(text);
        final int end = text.indexOf(')') + 1;
        spannable.setSpan(new ForegroundColorSpan(color), 0, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        binding.currencyChangeTextView.setText(spannable);
    }

    private void clearFields() {
        binding.fromInputEditText.setText("");
        binding.toResultTextView.setText("");
    }

    private void convertUserInput() {
        final Editable from = binding.fromInputEditText.getText();
        if (from != null) {
            viewModel.convertUserInput(from.toString());
        }
    }

    private void swapCurrencies() {
        final Editable from = binding.fromInputEditText.getText();
        if (from != null) {
            viewModel.swapCurrencies(from.toString());
        }
    }
}