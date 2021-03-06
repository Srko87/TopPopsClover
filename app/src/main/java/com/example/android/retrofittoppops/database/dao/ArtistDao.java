package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;

import java.util.List;

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
}
