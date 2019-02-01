package com.example.android.retrofittoppops.view.main;

import android.app.Application;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.model.TrackArtistHelper;
import com.example.android.retrofittoppops.network.ApiClient;
import com.example.android.retrofittoppops.network.ApiService;
import com.example.android.retrofittoppops.repository.ChartRepository;
import com.example.android.retrofittoppops.repository.TrackRepository;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private ApiService apiService;
    private TrackRepository trackRepository;
    private ChartRepository chartRepository;
    private CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<List<TrackArtistHelper>> tracks = new MutableLiveData<>();
    // TODO
    // can we maybe create a BaseViewModel that has error handling in
    // in that case we can reuse the same code across the different ViewModels
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        apiService = ApiClient.getClient().create(ApiService.class);
        trackRepository = new TrackRepository(application);
        chartRepository = new ChartRepository(application);
    }

    private void insertTracks(List<ChartDataTracks> chartDataTracksList) {
        trackRepository.insertAllTracks(chartDataTracksList);
    }

    public MutableLiveData<List<TrackArtistHelper>> getTracks() {
        return tracks;
    }

    void fetchCharts() {
        disposables.add(apiService.getTopPops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    insertTracks(response.getChartTracks().getChartDataTracksList());
                    chartRepository.insertOrUpdateChart(new Date(), response.getChartTracks().getChartDataTracksList());
                }, error -> {
                    errorLiveData.setValue(error.getLocalizedMessage());
                }));
    }

    LiveData<ChartEntity> getChartLiveData() {
        return chartRepository.getChartLiveData();
    }

    List<TrackEntity> getTracksById(List<String> trackIds) {
        return trackRepository.getTracksById(trackIds);
    }

    List<ArtistEntity> getArtistsById(List<String> artistIds) {
        return trackRepository.getArtistsById(artistIds);
    }

    LiveData<String> onError() {
        return errorLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
