package com.example.android.retrofittoppops.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.ChartDao;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.view.MainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ChartRepository {

    private ChartDao chartDao;

    public ChartRepository(Application application) {
        TracksDatabase db = TracksDatabase.getDatabase(application);
        chartDao = db.chartDao();
    }

    public ChartEntity getLastChart() {
        return chartDao.getLastChart();
    }



    // start: insert functions
    // insert chart
    public void insertChart (MainActivity.ChartParams... chartParams) {
        new insertChartAsyncTask(chartDao).execute(chartParams);
    }

    private static class insertChartAsyncTask extends AsyncTask<MainActivity.ChartParams, Void, Void> {

        private ChartDao chartDao;

        insertChartAsyncTask(ChartDao chartDao) {
            this.chartDao = chartDao;
        }

        @Override
        protected Void doInBackground(MainActivity.ChartParams... chartParams) {

            Date date = chartParams[0].getDate();
            List<ChartDataTracks> chartDataTracksList = chartParams[0].getTrackList();

            List<String> trackList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                trackList.add(chartDataTracksList.get(i).getId());
            }

            ChartEntity chartEntity = new ChartEntity();
            chartEntity.setCreatedAt(date);
            chartEntity.setModifiedAt(date);
            chartEntity.setTracks(trackList);

            chartDao.insert(chartEntity);

            return null;
        }
    }
    // end: insert functions


    // start: update functions
    public void updateChart (MainActivity.ChartParams... chartParams) {
        new updateChartAsyncTask(chartDao).execute(chartParams);
    }

    private static class updateChartAsyncTask extends AsyncTask<MainActivity.ChartParams, Void, Void> {

        private ChartDao chartDao;

        updateChartAsyncTask(ChartDao chartDao) {
            this.chartDao = chartDao;
        }

        @Override
        protected Void doInBackground(MainActivity.ChartParams... chartParams) {

            Date date = chartParams[0].getDate();
            List<ChartDataTracks> chartDataTracksList = chartParams[0].getTrackList();
            int id = chartParams[0].getId();

            List<String> trackList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                trackList.add(chartDataTracksList.get(i).getId());
            }

            chartDao.updateChart(date, new Gson().toJson(trackList), id);

            return null;
        }
    }
    // end: update functions


}

