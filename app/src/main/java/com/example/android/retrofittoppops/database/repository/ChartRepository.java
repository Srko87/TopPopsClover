package com.example.android.retrofittoppops.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.ChartDao;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.utils.DateCompare;

import java.util.Date;


public class ChartRepository {

    private ChartDao chartDao;

    public ChartRepository(Application application) {
        TracksDatabase db = TracksDatabase.getDatabase(application);
        chartDao = db.chartDao();
    }

    public ChartEntity getLastChart() {
        return chartDao.getLastChart();
    }

    public void insertChartDate(Date date) {
        new insertChartDateAsyncTask(chartDao).execute(date);
    }


    public void insertChart(Date date) {

        ChartEntity chartEntity = getLastChart();

        if (chartEntity != null && DateCompare.isSameDay(chartEntity.getCreatedAt(), date)) {
            // modify existing row
        } else {
            // insert new row
        }
    }

    // TODO why static?
    // replace after with Executor
    private static class insertChartDateAsyncTask extends AsyncTask<Date, Void, Void> {

        private ChartDao chartDao;

        insertChartDateAsyncTask(ChartDao chartDao) {
            this.chartDao = chartDao;
        }


        @Override
        protected Void doInBackground(Date... dates) {

            ChartEntity entity = new ChartEntity();
            entity.setCreatedAt(dates[0]);
            entity.setModifiedAt(dates[0]);

            chartDao.insert(entity);
            return null;
        }
    }

}

