package com.example.android.retrofittoppops.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.AlbumDao;
import com.example.android.retrofittoppops.database.dao.ArtistDao;
import com.example.android.retrofittoppops.database.dao.TrackDao;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.threading.DefaultExecutorSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.lifecycle.LiveData;

public class TrackRepository {

    public static final String TAG = "track repository";
    private TrackDao trackDao;
    private AlbumDao albumDao;
    private ArtistDao artistDao;

    public TrackRepository(Application application) {

        TracksDatabase db = TracksDatabase.getDatabase(application);

        trackDao = db.trackDao();
        albumDao = db.albumDao();
        artistDao = db.artistDao();
    }

    public LiveData<List<TrackEntity>> getAllTracks() {
        return trackDao.getAllTracks();
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

    // Delete functions
    public void deleteAll() {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            trackDao.deleteAll();
            albumDao.deleteAll();
            artistDao.deleteAll();
        });
    }

    public interface AsyncResponse{
        void queryFinish(ArtistEntity artistEntity, int position);
    }

    // TODO Vida
    // replace with Executor, no Async tasks, all threading needs to be done with executers
    // try implementing this without position in method

    public interface ArtistResponse {
        void onQueryFinish (ArtistEntity artistEntity);
    }

    public void getArtistById(String id, ArtistResponse listener) {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            if (listener != null) {
                listener.onQueryFinish(artistDao.getArtistById(id));
            }
        });
    }

    public LiveData<List<TrackEntity>> getLastTracksById(List<String> trackIdList) {
        return trackDao.getLastTracksById(trackIdList);
    }

    public LiveData<List<ArtistEntity>> getArtistsById(Set<String> artistIdList) {
        return artistDao.getArtistsById(artistIdList);
    }

    public interface TrackResponse {
        void onQueryFinish(TrackEntity trackEntity);
    }

    public void getTrackById(String id, TrackResponse listener) {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            if (listener != null) {
                listener.onQueryFinish(trackDao.getTrackById(id));
            }
        });
    }

    public List<TrackEntity> getTracksById(List<String> trackIds){
        return trackDao.getTracksById(trackIds);
    }

}
