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

    @Query("SELECT * FROM tracks_table WHERE id IN (:trackIdList)")
    LiveData<List<TrackEntity>> getLastTracksById(List<String> trackIdList);

    // TODO track order needs to be valid, chart order from top to bottom
    // check track order
    // sort by position
    @Query("SELECT * FROM tracks_table WHERE id IN (:trackIdList)")
    List<TrackEntity> getTracksById(List<String> trackIdList);
}
