package com.example.android.retrofittoppops.rest;

import android.widget.Toast;

import com.example.android.retrofittoppops.model.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO
// try this out
public class CustomCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() instanceof BaseModel) {
            if ( response.code() == 200) {
                onSuccess(call, response);
            } else {
                // show error dialog
//                Toast.makeText(getApplication(),"onResponse err", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSuccess(Call<T> call, Response<T> response) {
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
