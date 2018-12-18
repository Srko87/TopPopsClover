package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.Track;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface TrackDao {

    @Query("DELETE FROM tracks_table")
    void deleteAll();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Track... tracks);

    @Query("SELECT * FROM tracks_table")
    LiveData<List<Track>> getAllTracks();

}
