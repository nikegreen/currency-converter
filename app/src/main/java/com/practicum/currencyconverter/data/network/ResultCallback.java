package com.practicum.currencyconverter.data.network;

public interface ResultCallback<T> {

    void onSuccess(final T data);

    void onFailure(final Throwable error);
}
