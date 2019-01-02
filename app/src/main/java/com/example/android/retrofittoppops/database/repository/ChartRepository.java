package com.example.android.retrofittoppops.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.ChartDao;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.utils.DateCompare;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.threading.DefaultExecutorSupplier;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;


public class ChartRepository {

    private ChartDao chartDao;

    public ChartRepository(Application application) {
        TracksDatabase db = TracksDatabase.getDatabase(application);
        chartDao = db.chartDao();
    }

    public ChartEntity getMostRecentChart() {
        return chartDao.getMostRecentChart();
    }


    public void insertOrUpdateChart(Date date, List<ChartDataTracks> chartDataTracksList) {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            ChartEntity chartEntity = getMostRecentChart();
            if (chartEntity != null && DateCompare.isSameDay(chartEntity.getCreatedAt(), date)) {
                // modify existing row
                DateChartParams dateChartParams = new DateChartParams(date, chartDataTracksList, chartEntity.getId());
                updateChart(dateChartParams);
            } else {
                // insert new row
                DateChartParams dateChartParams = new DateChartParams(date, chartDataTracksList);
                insertChart(dateChartParams);
            }
        });
    }




    public void insertChart(@NonNull DateChartParams dateChartParams) {
        Date date = dateChartParams.getDate();
        List<ChartDataTracks> chartDataTracksList = dateChartParams.getChartDataTracksList();

        List<String> trackList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            trackList.add(chartDataTracksList.get(i).getId());
        }

        ChartEntity chartEntity = new ChartEntity();
        chartEntity.setCreatedAt(date);
        chartEntity.setModifiedAt(date);
        chartEntity.setTracks(trackList);

        chartDao.insert(chartEntity);
    }

    private void updateChart(@NonNull DateChartParams dateChartParams) {
        Date date = dateChartParams.getDate();
        List<ChartDataTracks> chartDataTracksList = dateChartParams.getChartDataTracksList();
        int id = dateChartParams.getId();

        List<String> trackList = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            trackList.add(chartDataTracksList.get(i).getId());
        }

        chartDao.updateChart(date, new Gson().toJson(trackList), id);

    }




    public static class DateChartParams {
        private Date date;
        private List<ChartDataTracks> chartDataTracksList;
        private int id;

        public DateChartParams(Date date, List<ChartDataTracks> chartDataTracksList, int id) {

            this.date = date;
            this.chartDataTracksList = chartDataTracksList;
            this.id = id;
            }

            public DateChartParams(Date date, List<ChartDataTracks> chartDataTracksList) {
            this.date = date;
            this.chartDataTracksList = chartDataTracksList;
            }

            public Date getDate() {return date;}
            public List<ChartDataTracks> getChartDataTracksList() { return chartDataTracksList; }
            public int getId() { return id; }
    }

}

