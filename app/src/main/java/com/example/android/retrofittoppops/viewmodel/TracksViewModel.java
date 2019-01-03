package com.example.android.retrofittoppops.viewmodel;

import android.app.Application;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.database.repository.ChartRepository;
import com.example.android.retrofittoppops.database.repository.TrackRepository;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.model.Chart.ChartTopPops;
import com.example.android.retrofittoppops.rest.ApiClient;
import com.example.android.retrofittoppops.rest.ApiInterface;
import com.example.android.retrofittoppops.rest.CustomCallback;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksViewModel extends AndroidViewModel {

    private TrackRepository trackRepository;
    private ChartRepository chartRepository;
    private ChartTopPops chartTopPops;


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

    private void insertTracks( List<ChartDataTracks> chartDataTracksList) {
        trackRepository.insertAllTracks(chartDataTracksList);
    }

    public void deleteAll() {
        trackRepository.deleteAll();
    }

    public void getArtist(String id, int position, TrackRepository.AsyncResponse delegate) {
        trackRepository.getArtist(id, position, delegate);
    }

    public void insertOrUpdateChart(Date date, List<ChartDataTracks> chartDataTracksList) {
        chartRepository.insertOrUpdateChart(date, chartDataTracksList);
    }

    public void fetchCharts (RecyclerView recyclerView, TextView textView) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ChartTopPops> call = apiService.getTopPops();
        call.enqueue(new CustomCallback<ChartTopPops>() {
            @Override
            public void onSuccess(Call<ChartTopPops> call, Response<ChartTopPops> response) {
                try{
                    Date date = new Date();
                    chartTopPops = response.body();
                    insertTracks(chartTopPops.getChartTracks().getChartDataTracksList());
                    recyclerView.setVisibility(View.VISIBLE);


                    insertOrUpdateChart(date, chartTopPops.getChartTracks().getChartDataTracksList());

                } catch (Exception e) {
                    Toast.makeText(getApplication(),"onResponse err", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChartTopPops> call, Throwable t) {
                Toast.makeText(getApplication(), "onFailure err", Toast.LENGTH_SHORT).show();
                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });

    }}
