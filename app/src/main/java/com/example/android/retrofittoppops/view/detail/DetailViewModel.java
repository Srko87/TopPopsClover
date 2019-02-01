package com.example.android.retrofittoppops.view.detail;

import android.app.Application;

import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.network.ApiClient;
import com.example.android.retrofittoppops.network.ApiService;
import com.example.android.retrofittoppops.repository.AlbumRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends AndroidViewModel {

    private ApiService apiService;
    private AlbumRepository albumRepository;

    private CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

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
                // TODO
                // if error occurred the response part is still run, crashing the app
                // implement on success and on failure methods
                .subscribe(response -> albumRepository.updateAlbum(response.getAlbumArtist().getName(), response.getCover(), response.getTracks().getAlbumTracksData(), response.getId()),
                        error -> {
                            errorLiveData.setValue(error.getLocalizedMessage());
                        }));
    }

    LiveData<String> onError() {
        return errorLiveData;
    }
}
