package com.example.android.retrofittoppops.database;

import android.content.Context;

import com.example.android.retrofittoppops.database.dao.AlbumDao;
import com.example.android.retrofittoppops.database.dao.ArtistDao;
import com.example.android.retrofittoppops.database.dao.ChartDao;
import com.example.android.retrofittoppops.database.dao.TrackDao;
import com.example.android.retrofittoppops.database.entity.Album;
import com.example.android.retrofittoppops.database.entity.Artist;
import com.example.android.retrofittoppops.database.entity.Chart;
import com.example.android.retrofittoppops.database.entity.Track;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Album.class, Artist.class, Chart.class, Track.class}, version = 1)
public abstract class TracksDatabase extends RoomDatabase {


    public abstract AlbumDao albumDao();

    public abstract ArtistDao artistDao();

    public abstract ChartDao chartDao();

    public abstract TrackDao trackDao();

    private static final String DB_NAME = "tracks_database";

    private static volatile TracksDatabase INSTANCE;

    public static TracksDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (TracksDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TracksDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
