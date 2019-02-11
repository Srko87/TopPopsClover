package com.example.android.retrofittoppops.commons.baseClasses;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    protected MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public LiveData<String> onError() {
        return errorLiveData;
    }

}
