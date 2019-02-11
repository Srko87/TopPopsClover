package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ArtistEntity... artistEntities);

    @Query("SELECT * FROM artist_table WHERE id = :id")
    ArtistEntity getArtist(String id);

    @Query("DELETE FROM artist_table")
    void deleteAll();

    @Query("SELECT * FROM artist_table WHERE id IN (:artistIdList)")
    List<ArtistEntity> getArtistsById(List<String> artistIdList);
}
