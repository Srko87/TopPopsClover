package com.example.android.retrofittoppops.viewmodel;

import android.app.Application;
import android.widget.Toast;

import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.database.repository.ChartRepository;
import com.example.android.retrofittoppops.database.repository.TrackRepository;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.model.Chart.ChartTopPops;
import com.example.android.retrofittoppops.rest.ApiClient;
import com.example.android.retrofittoppops.rest.ApiService;


import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class TracksViewModel extends AndroidViewModel {

    private TrackRepository trackRepository;
    private ChartRepository chartRepository;
    private CompositeDisposable disposables = new CompositeDisposable();


    public TracksViewModel(@NonNull Application application) {
        super(application);
        trackRepository = new TrackRepository(application);
        chartRepository = new ChartRepository(application);
    }

    // TODO
    // make LiveData from JOIN query
    // fetch TrackArtistHelper from database
    public LiveData<List<TrackEntity>> getAllTracks() {
        return trackRepository.getAllTracks();
    }

    private void insertTracks(List<ChartDataTracks> chartDataTracksList) {
        trackRepository.insertAllTracks(chartDataTracksList);
    }

    public void deleteAll() {
        trackRepository.deleteAll();
    }

    public void getArtist(String id, int position, TrackRepository.AsyncResponse listener) {
        trackRepository.getArtist(id, position, listener);
    }

    private void insertOrUpdateChart(Date date, List<ChartDataTracks> chartDataTracksList) {
        chartRepository.insertOrUpdateChart(date, chartDataTracksList);
    }

    public void fetchCharts() {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        disposables.add(apiService.getTopPops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    insertTracks(response.getChartTracks().getChartDataTracksList());
                    insertOrUpdateChart(new Date(), response.getChartTracks().getChartDataTracksList());
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
