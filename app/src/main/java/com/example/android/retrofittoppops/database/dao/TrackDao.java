package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.TrackEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TrackDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TrackEntity... trackEntities);

    @Query("SELECT * FROM tracks_table")
    LiveData<List<TrackEntity>> getAllTracks();

    @Query("DELETE FROM tracks_table")
    void deleteAll();


    @Query("SELECT * FROM tracks_table WHERE id = :id")
    TrackEntity getTrackById(String id);
}
