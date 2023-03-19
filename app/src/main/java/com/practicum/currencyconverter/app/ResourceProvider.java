package com.practicum.currencyconverter.app;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.StringRes;

public class ResourceProvider {

    private final Resources resources;

    public ResourceProvider(final Context context) {
        resources = context.getResources();
    }

    public String getString(@StringRes final int resId) {
        return resources.getString(resId);
    }
}
