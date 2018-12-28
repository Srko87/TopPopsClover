package com.example.android.retrofittoppops.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "album_table", foreignKeys =  @ForeignKey(entity = ArtistEntity.class, parentColumns = "id",
        childColumns = "artistId", onDelete = CASCADE), indices = {@Index("artistId")})
public class AlbumEntity {

    @NonNull
    @PrimaryKey
    private Integer id;

    private String name;

    private Integer artistId;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
