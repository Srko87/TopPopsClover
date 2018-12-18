package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.Artist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface ArtistDao {

    @Query("DELETE FROM artist_table")
    void deleteAll();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Artist... artists);

    @Query("SELECT * FROM artist_table")
    LiveData<List<Artist>> getAllArtists();

}
