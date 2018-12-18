package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.Chart;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ChartDao {

    @Insert
    void insert(Chart chart);

    @Query("DELETE FROM chart_table")
    void deleteAll();

    @Query("SELECT * FROM chart_table ORDER BY id DES LIMIT 1")
    Chart getLastChart();

    //TODO Make query for dates

}
