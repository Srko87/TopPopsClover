package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.TrackArtist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface TrackArtistDao {

    @Query("SELECT * FROM artist_table INNER JOIN tracks_table ON tracks_table.artistId = artist_table.id")
    LiveData<List<TrackArtist>> getAllTracks();

}
