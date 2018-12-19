package com.example.android.retrofittoppops.database.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class TrackConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToList(String data) {
        Type dataType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(data, dataType);
    }

    @TypeConverter
    public static String listToString(List<String> data) {
        return gson.toJson(data);
    }
}
