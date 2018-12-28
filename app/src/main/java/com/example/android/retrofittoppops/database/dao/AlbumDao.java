package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.AlbumEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AlbumEntity... albumEntities);

    @Query("DELETE FROM album_table")
    void deleteAll();

}
