package com.example.android.retrofittoppops.database.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.android.retrofittoppops.database.TracksDatabase;
import com.example.android.retrofittoppops.database.dao.AlbumDao;
import com.example.android.retrofittoppops.database.dao.ArtistDao;
import com.example.android.retrofittoppops.database.dao.TrackDao;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

public class TrackRepository {

    private TrackDao trackDao;
    private AlbumDao albumDao;
    private ArtistDao artistDao;

    public static final String TAG = "track repository";

    public TrackRepository(Application application) {

        TracksDatabase db = TracksDatabase.getDatabase(application);

        trackDao = db.trackDao();
        albumDao = db.albumDao();
        artistDao = db.artistDao();
    }


    public LiveData<List<TrackEntity>> getAllTracks() {
        return trackDao.getAllTracks();
    }

    // Insert functions
    public void insertAllTracks(ChartDataTracks... track) {
        new insertAllAsyncTask(trackDao, albumDao,artistDao).execute(track);
    }

    private static class insertAllAsyncTask extends AsyncTask<ChartDataTracks, Void, Void> {

        private TrackDao trackDao;
        private AlbumDao albumDao;
        private ArtistDao artistDao;


        insertAllAsyncTask(TrackDao trackDao, AlbumDao albumDao, ArtistDao artistDao) {
            this.trackDao = trackDao;
            this.albumDao = albumDao;
            this.artistDao = artistDao;


        }

        @Override
        protected Void doInBackground(ChartDataTracks... chartDataTracks) {
            Log.i(TAG,"inserting into db");
            List<TrackEntity> trackEntities = new ArrayList<>();
            List<AlbumEntity> albumEntities = new ArrayList<>();
            List<ArtistEntity> artistEntities = new ArrayList<>();

            for (ChartDataTracks cDTracks : chartDataTracks) {

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


            Log.i(TAG, "inserting done");
            return null;
        }
    }

    // Delete functions
    public void deleteAll() {
        new deleteAllAsyncTask(trackDao, albumDao, artistDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private TrackDao trackDao;
        private AlbumDao albumDao;
        private ArtistDao artistDao;

        deleteAllAsyncTask(TrackDao trackDao, AlbumDao albumDao, ArtistDao artistDao) {

            this.trackDao = trackDao;
            this.albumDao = albumDao;
            this.artistDao = artistDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            trackDao.deleteAll();
            albumDao.deleteAll();
            artistDao.deleteAll();
            return null;
        }
    }
}
