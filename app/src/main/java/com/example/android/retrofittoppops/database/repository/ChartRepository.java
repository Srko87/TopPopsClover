package com.example.android.retrofittoppops.database.repository;

import android.app.Application;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.ChartDao;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.database.utils.DateCompare;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.threading.DefaultExecutorSupplier;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

// TODO Vida
// checkout optimised/refactored code
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

            ChartEntity lastChartEntity = getMostRecentChart();

            List<String> trackList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                trackList.add(chartDataTracksList.get(i).getId());
            }

            if (lastChartEntity != null && DateCompare.isSameDay(lastChartEntity.getCreatedAt(), date)) {
                // modify existing row
                updateChart(date, trackList, lastChartEntity.getId());
            } else {
                // insert new row
                ChartEntity chartEntity = new ChartEntity();
                chartEntity.setCreatedAt(date);
                chartEntity.setModifiedAt(date);
                chartEntity.setTracks(trackList);

                insertChart(chartEntity);
            }
        });
    }

    public LiveData<ChartEntity> getLastChartLiveData() {
        return chartDao.getMostRecentChartLiveData();
    }

    private void insertChart(ChartEntity chartEntity) {
        chartDao.insert(chartEntity);
    }

    private void updateChart(Date date, List<String> trackList, int id) {
        chartDao.updateChart(date, new Gson().toJson(trackList), id);
    }
}

