package com.example.android.retrofittoppops.repository;

import android.app.Application;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.AlbumDao;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.model.Album.AlbumTracksData;
import com.example.android.retrofittoppops.commons.thread.DefaultExecutorSupplier;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class AlbumRepository {

    private AlbumDao albumDao;

    public AlbumRepository(Application application) {

        TracksDatabase db = TracksDatabase.getDatabase(application);
        albumDao = db.albumDao();
    }

    public void updateAlbum(String artistName, String cover, List<AlbumTracksData> trackList, String id) {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

            AlbumEntity albumEntity = new AlbumEntity();
            albumEntity.setArtistName(artistName);
            albumEntity.setCover(cover);

            List<String> trackTitleList = new ArrayList<>();

            for (AlbumTracksData albumTracks : trackList) {
                trackTitleList.add(albumTracks.getTitle());
            }

            albumEntity.setId(id);
            albumDao.updateAlbum(artistName, cover, new Gson().toJson(trackTitleList), id);
        });
    }

    public LiveData<AlbumEntity> getAlbum(String id) {
        return albumDao.getAlbum(id);
    }
}
