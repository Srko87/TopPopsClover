package com.example.android.retrofittoppops.database.repository;

import android.app.Application;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.ChartDao;
import com.example.android.retrofittoppops.database.entity.ChartEntity;




public class ChartRepository {

    private ChartDao chartDao;

    public ChartRepository(Application application) {
        TracksDatabase db = TracksDatabase.getDatabase(application);
        chartDao = db.chartDao();
    }

    public ChartEntity getLastChart() {
        return chartDao.getLastChart();
    }






}

