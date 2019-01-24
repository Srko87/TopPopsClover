package com.example.android.retrofittoppops.viewmodel;

import android.app.Application;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.database.repository.ChartRepository;
import com.example.android.retrofittoppops.database.repository.TrackRepository;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.model.TrackArtistHelper;
import com.example.android.retrofittoppops.rest.ApiClient;
import com.example.android.retrofittoppops.rest.ApiService;


import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class TracksViewModel extends AndroidViewModel {

    private TrackRepository trackRepository;
    private ChartRepository chartRepository;
    private CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<List<TrackArtistHelper>> tracks = new MutableLiveData<>();

    public MutableLiveData<List<TrackArtistHelper>> getTracks() {
        return tracks;
    }

    public TracksViewModel(@NonNull Application application) {
        super(application);
        trackRepository = new TrackRepository(application);
        chartRepository = new ChartRepository(application);
    }

    private void insertTracks(List<ChartDataTracks> chartDataTracksList) {
        trackRepository.insertAllTracks(chartDataTracksList);
    }

    public void deleteAll() {
        trackRepository.deleteAll();
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

    public LiveData<ChartEntity> getChartLiveData() {
        return chartRepository.getChartLiveData();
    }

    public LiveData<List<TrackEntity>> getTracks(List<String> tracksId) {
        return trackRepository.getLastTracksById(tracksId);
    }

    public List<TrackEntity> getTracksById(List<String> trackIds) {
        return trackRepository.getTracksById(trackIds);
    }

    public List<ArtistEntity> getArtistsById(List<String> artistIds) {
        return trackRepository.getArtistsById(artistIds);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
