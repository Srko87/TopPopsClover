package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.AlbumEntity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AlbumEntity... albumEntities);

    @Query("DELETE FROM album_table")
    void deleteAll();

    @Query("SELECT * FROM album_table WHERE id = :id")
    LiveData<AlbumEntity> getAlbum(String id);

    @Query("UPDATE album_table SET artistName = :artistName, coverBig = :cover, trackList = :trackList WHERE id = :id ")
    void updateAlbum(String artistName, String cover, String trackList, String id);

}
