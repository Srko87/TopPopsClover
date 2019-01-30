package com.example.android.retrofittoppops.database;

import android.content.Context;

import com.example.android.retrofittoppops.database.dao.AlbumDao;
import com.example.android.retrofittoppops.database.dao.ArtistDao;
import com.example.android.retrofittoppops.database.dao.ChartDao;
import com.example.android.retrofittoppops.database.dao.TrackDao;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.ChartEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.database.utils.DateConverter;
import com.example.android.retrofittoppops.database.utils.TrackConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {AlbumEntity.class, ArtistEntity.class, ChartEntity.class, TrackEntity.class}, version = 10)
@TypeConverters({DateConverter.class, TrackConverter.class})
public abstract class TracksDatabase extends RoomDatabase {

    private static final String DB_NAME = "tracks_database";
    private static volatile TracksDatabase instance;

    public static TracksDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (TracksDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), TracksDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract TrackDao trackDao();
    public abstract AlbumDao albumDao();
    public abstract ArtistDao artistDao();
    public abstract ChartDao chartDao();

}
