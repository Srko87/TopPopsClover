package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.TrackEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface TrackDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TrackEntity... trackEntities);

    @Query("SELECT * FROM tracks_table")
    LiveData<List<TrackEntity>> getAllTracks();

    @Query("DELETE FROM tracks_table")
    void deleteAll();


//    @Query("SELEadCT * FROM artist_table INNER JOIN tracks_table ON tracks_table.artistId = artist_table.id")
//    LiveData<List<TrackArtistHelper>> getAllTracksArtists();
}
