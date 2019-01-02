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
    private String id;

    private String name;

    private String artistId;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
