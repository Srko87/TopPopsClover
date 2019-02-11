package com.example.android.retrofittoppops.commons.utils;

import java.util.Locale;

public class Tools {

    public static String secondsToString(int duration) {
        return String.format(Locale.getDefault(), "%02d:%02d", duration / 60, duration % 60);
    }
}
