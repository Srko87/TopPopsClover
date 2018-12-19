package com.example.android.retrofittoppops.database.dao;

import com.example.android.retrofittoppops.database.entity.Chart;

import java.util.Date;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ChartDao {

    @Insert
    void insert(Chart chart);

    @Query("SELECT * FROM chart_table ORDER BY id DESC LIMIT 1")
    Chart getLastChart();

    @Query("DELETE FROM chart_table")
    void deleteAll();

    @Query("UPDATE chart_table SET modifiedAt = :date, tracks = :tracklist WHERE id = :id")
    void updateChart(Date date, String tracklist, int id);

}
