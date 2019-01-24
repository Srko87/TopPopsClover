package com.example.android.retrofittoppops.database.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tracks_table",
        foreignKeys = { @ForeignKey(entity = ArtistEntity.class, parentColumns = "id", childColumns = "artistId", onDelete = CASCADE, onUpdate = CASCADE),
                @ForeignKey(entity = AlbumEntity.class, parentColumns = "id", childColumns = "albumId", onDelete = CASCADE, onUpdate = CASCADE)},
        indices = {@Index("artistId"), @Index("albumId")})

public class TrackEntity {

    // TODO
    // make id primary key
    private String id;

    @NonNull
    @PrimaryKey
    private Integer position;

    private String title;
    private Integer duration;
    private String artistId;
    private String albumId;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
}
