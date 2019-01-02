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

    // Insert functions

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

    public void getArtist(String id, int position, AsyncResponse delegate) {
        new GetArtistAsync(artistDao, position, delegate).execute(id);
    }

    private static class GetArtistAsync extends AsyncTask<String, Void, ArtistEntity> {

        public AsyncResponse delegate;

        private ArtistDao artistDao;

        private int position;

        GetArtistAsync(ArtistDao artistDao, int position, AsyncResponse delegate) {
            this.artistDao = artistDao;
            this.position = position;
            this.delegate = delegate;

        }

        @Override
        protected ArtistEntity doInBackground(String... ids) {
            return artistDao.getArtist(ids[0]);
        }

        @Override
        protected void onPostExecute(ArtistEntity artistEntity) {
            super.onPostExecute(artistEntity);
            delegate.queryFinish(artistEntity, position);
        }
    }

}
