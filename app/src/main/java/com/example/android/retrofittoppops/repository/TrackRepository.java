package com.example.android.retrofittoppops.repository;

import android.app.Application;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.AlbumDao;
import com.example.android.retrofittoppops.database.dao.ArtistDao;
import com.example.android.retrofittoppops.database.dao.TrackDao;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.commons.thread.DefaultExecutorSupplier;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class TrackRepository {

    private TrackDao trackDao;
    private AlbumDao albumDao;
    private ArtistDao artistDao;

    public TrackRepository(Application application) {

        TracksDatabase db = TracksDatabase.getDatabase(application);

        trackDao = db.trackDao();
        albumDao = db.albumDao();
        artistDao = db.artistDao();
    }

    public void insertAllTracks(List<ChartDataTracks> chartDataTracksList) {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

            List<TrackEntity> trackEntities = new ArrayList<>();
            List<AlbumEntity> albumEntities = new ArrayList<>();
            List<ArtistEntity> artistEntities = new ArrayList<>();

            for (ChartDataTracks cDTracks : chartDataTracksList) {

                ArtistEntity artistEntity = new ArtistEntity();
                artistEntity.setId(cDTracks.getChartArtistTracks().getId());
                artistEntity.setName(cDTracks.getChartArtistTracks().getName());
                artistEntities.add(artistEntity);

                AlbumEntity albumEntity = new AlbumEntity();
                albumEntity.setId(cDTracks.getChartAlbumTracks().getId());
                albumEntity.setName(cDTracks.getChartAlbumTracks().getTitle());
                albumEntity.setArtistId(cDTracks.getChartArtistTracks().getId());
                albumEntities.add(albumEntity);

                TrackEntity trackEntity = new TrackEntity();
                trackEntity.setId(cDTracks.getId());
                trackEntity.setTitle(cDTracks.getTitle());
                trackEntity.setDuration(cDTracks.getDuration());
                trackEntity.setPosition(cDTracks.getPosition());
                trackEntity.setAlbumId(cDTracks.getChartAlbumTracks().getId());
                trackEntity.setArtistId(cDTracks.getChartArtistTracks().getId());
                trackEntities.add(trackEntity);
            }

            artistDao.insert(artistEntities.toArray(new ArtistEntity[0]));
            albumDao.insert(albumEntities.toArray(new AlbumEntity[0]));
            trackDao.insert(trackEntities.toArray(new TrackEntity[0]));

        });
    }

    public LiveData<List<TrackEntity>> getLastTracksById(List<String> trackIdList) {
        return trackDao.getLastTracksById(trackIdList);
    }

    public List<TrackEntity> getTracksById(List<String> trackIds) {
        return trackDao.getTracksById(trackIds);
    }

    public List<ArtistEntity> getArtistsById(List<String> artistIds) {
        return artistDao.getArtistsById(artistIds);
    }

}
