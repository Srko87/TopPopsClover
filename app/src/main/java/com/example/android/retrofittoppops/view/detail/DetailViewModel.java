package com.example.android.retrofittoppops.view.detail;

import android.app.Application;

import com.example.android.retrofittoppops.commons.baseClasses.BaseViewModel;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.network.ApiClient;
import com.example.android.retrofittoppops.network.ApiService;
import com.example.android.retrofittoppops.repository.AlbumRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends BaseViewModel {

    private ApiService apiService;
    private AlbumRepository albumRepository;

    private CompositeDisposable disposables = new CompositeDisposable();

    public DetailViewModel(@NonNull Application application) {
        super(application);
        apiService = ApiClient.getClient().create(ApiService.class);
        albumRepository = new AlbumRepository(application);
    }

    LiveData<AlbumEntity> getAlbumLiveData(String id) {
        return albumRepository.getAlbum(id);
    }

    void fetchAlbumFromApi(String id) {
        disposables.add(apiService.getAlbum(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.getErrorResponse() == null) {
                                albumRepository.updateAlbum(response.getAlbumArtist().getName(),
                                        response.getCoverBig(), response.getTracks().getAlbumTracksData(), response.getId());
                            } else {
                                errorLiveData.setValue(response.getErrorResponse().getMessage());
                            }
                        },
                        throwable -> errorLiveData.setValue(throwable.getLocalizedMessage())
                ));
    }
}
