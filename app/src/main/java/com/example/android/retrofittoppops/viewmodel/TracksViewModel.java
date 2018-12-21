package com.example.android.retrofittoppops.viewmodel;

import android.app.Application;

import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.database.repository.TrackRepository;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TracksViewModel extends AndroidViewModel {

    private TrackRepository trackRepository;

    public TracksViewModel(@NonNull Application application) {
        super(application);
        trackRepository = new TrackRepository(application);
    }

    public LiveData<List<TrackEntity>> getAllTracks() {
        return trackRepository.getAllTracks();
    }
    public void insertTracks(ChartDataTracks... chartDataTracks) {
        trackRepository.insertAllTracks(chartDataTracks);
    }

    public void deleteAll() {
        trackRepository.deleteAll();
    }


}
