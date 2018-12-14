package com.example.android.retrofittoppops.utils;

public class Tools {

    public static String secondsToString(int duration){
        return String.format("%02d:%02d", duration / 60, duration % 60);
    }
}
