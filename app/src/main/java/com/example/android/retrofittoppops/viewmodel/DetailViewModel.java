package com.example.android.retrofittoppops.viewmodel;

import android.app.Application;

import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.database.repository.AlbumRepository;
import com.example.android.retrofittoppops.model.Album.AlbumTracksData;
import com.example.android.retrofittoppops.rest.ApiClient;
import com.example.android.retrofittoppops.rest.ApiService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends AndroidViewModel {

    private AlbumRepository albumRepository;
    private CompositeDisposable disposables = new CompositeDisposable();

    public DetailViewModel(@NonNull Application application) {
        super(application);
        albumRepository = new AlbumRepository(application);
    }

    private void updateAlbum(String artistName, String cover, List<AlbumTracksData> trackList, String id) {
        albumRepository.updateAlbum(artistName, cover, trackList, id);
    }

    public LiveData<AlbumEntity> getAlbumLiveData(String id) {
        return albumRepository.getAlbum(id);
    }

    public void fetchAlbumFromApi(String id) {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        disposables.add(apiService.getAlbum(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                    updateAlbum(response.getAlbumArtist().getName(), response.getCover(), response.getTracks().getAlbumTracksData(), response.getId());
                }));
    }
}
