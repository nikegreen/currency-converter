package com.practicum.currencyconverter.presentation.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    protected final MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>(false);

    public LiveData<Boolean> isLoadingLiveData() {
        return isLoadingLiveData;
    }

    protected final MutableLiveData<Boolean> isErrorShownLiveData = new MutableLiveData<>();

    public LiveData<Boolean> isErrorShownLiveData() {
        return isErrorShownLiveData;
    }
}
