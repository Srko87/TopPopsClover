package com.example.android.retrofittoppops.viewmodel;

import android.app.Application;
import android.widget.Toast;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.database.repository.ChartRepository;
import com.example.android.retrofittoppops.database.repository.TrackRepository;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.model.Chart.ChartTopPops;
import com.example.android.retrofittoppops.model.TrackArtistHelper;
import com.example.android.retrofittoppops.rest.ApiClient;
import com.example.android.retrofittoppops.rest.ApiService;


import java.util.ArrayList;
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
    private MutableLiveData<ArtistEntity> artistEntityLiveData = new MutableLiveData<>();
    private List<TrackArtistHelper> helperList = new ArrayList<>();


    public TracksViewModel(@NonNull Application application) {
        super(application);
        trackRepository = new TrackRepository(application);
        chartRepository = new ChartRepository(application);
    }

    // TODO FIXME
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

    public void getArtistById(String id) {
        trackRepository.getArtistById(id, artistEntity -> artistEntityLiveData.postValue(artistEntity));
    }

    public LiveData<ArtistEntity> getArtistLiveData() {
        return  artistEntityLiveData;
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

    public LiveData<ChartEntity> getLastChartLiveData() {
        return chartRepository.getLastChartLiveData();
    }

    public void getTrackHelper(List<String> id) {
        helperList.clear();
        for (int i = 0; i < id.size(); i++) {
            TrackArtistHelper helper = new TrackArtistHelper();
            trackRepository.getTrackById(id.get(i), trackEntity -> {
                helper.setTrack(trackEntity);
                trackRepository.getArtistById(trackEntity.getArtistId(), artistEntity -> {
                    helper.setArtist(artistEntity);
                    helperList.add(helper);
                });
            });
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
