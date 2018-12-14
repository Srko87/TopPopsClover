package com.example.android.retrofittoppops.model.Album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlbumTracksDataArtist implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("tracklist")
    private String tracklist;
    @SerializedName("type")
    private String type;

    AlbumTracksDataArtist(Integer id, String name, String tracklist, String type) {
        this.id = id;
        this.name = name;
        this.tracklist = tracklist;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AlbumTracksDataArtist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tracklist='" + tracklist + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
