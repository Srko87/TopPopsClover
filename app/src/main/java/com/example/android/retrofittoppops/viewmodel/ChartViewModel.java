package com.example.android.retrofittoppops.viewmodel;

import android.app.Application;

import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.repository.ChartRepository;
import com.example.android.retrofittoppops.view.MainActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ChartViewModel extends AndroidViewModel {

    private ChartRepository chartRepository;

    public ChartViewModel(@NonNull Application application) {
        super(application);
        chartRepository = new ChartRepository(application);
    }


    public void insertChart(MainActivity.ChartParams chartParams) {
        chartRepository.insertChart(chartParams);
    }

    public ChartEntity getLastChart() {
        return chartRepository.getLastChart();
    }

    public void updateChart(MainActivity.ChartParams chartParams) {
        chartRepository.updateChart(chartParams);
    }



}
