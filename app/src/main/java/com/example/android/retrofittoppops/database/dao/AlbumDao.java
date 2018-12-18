package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.Album;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface AlbumDao {

    @Query("DELETE FROM album_table")
    void deleteAll();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Album... albums);

    @Query("SELECT * FROM album_table")
    LiveData<List<Album>> getAllAlbums();
}
