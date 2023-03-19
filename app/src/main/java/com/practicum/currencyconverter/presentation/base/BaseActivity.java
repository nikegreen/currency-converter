package com.practicum.currencyconverter.presentation.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<ViewModel extends BaseViewModel> extends AppCompatActivity {

    protected ViewModel viewModel;

    protected abstract ViewModel createViewModel();

    protected void showLoader(final boolean isLoading) {
    }

    protected void handleError(final boolean isShown) {
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();

        viewModel.isErrorShownLiveData().observe(this, this::handleError);
        viewModel.isLoadingLiveData().observe(this, this::showLoader);
    }
}
