package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.ChartEntity;

import java.util.Date;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ChartDao {

    @Insert
    void insert(ChartEntity chartEntity);

    @Query("SELECT * FROM chart_table ORDER BY id DESC LIMIT 1")
    ChartEntity getLastChart();

    @Query("DELETE FROM chart_table")
    void deleteAll();


}
