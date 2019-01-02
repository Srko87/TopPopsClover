package com.example.android.retrofittoppops.database.entity;

import com.example.android.retrofittoppops.database.utils.DateConverter;
import com.example.android.retrofittoppops.database.utils.TrackConverter;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "chart_table")
public class ChartEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @TypeConverters(DateConverter.class)
    private Date createdAt;
    @TypeConverters(DateConverter.class)
    private Date modifiedAt;
    @TypeConverters(TrackConverter.class)
    private List<String> tracks;


    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public List<String> getTracks() { return tracks; }

    public void setTracks(List<String> tracks) { this.tracks = tracks; }
}
