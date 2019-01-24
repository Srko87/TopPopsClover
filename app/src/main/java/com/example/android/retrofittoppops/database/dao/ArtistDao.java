package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;

import java.util.List;
import java.util.Set;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ArtistEntity... artistEntities);

    @Query("SELECT * FROM artist_table WHERE id = :id")
    ArtistEntity getArtist(String id);

    @Query("DELETE FROM artist_table")
    void deleteAll();

    @Query("SELECT * FROM artist_table WHERE id = :id")
    ArtistEntity getArtistById(String id);

    @Query("SELECT * FROM artist_table WHERE id IN (:artistIdList)")
    LiveData <List<ArtistEntity>> getArtistsById(Set<String> artistIdList);

}
