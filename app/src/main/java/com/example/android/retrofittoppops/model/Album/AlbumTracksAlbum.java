package com.example.android.retrofittoppops.model.Album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class AlbumTracksAlbum implements Serializable {

    @SerializedName("data")
    private ArrayList<AlbumTracksData> albumTracksData;

    public ArrayList<AlbumTracksData> getAlbumTracksData() {
        return albumTracksData;
    }

    public void setAlbumTracksData(ArrayList<AlbumTracksData> albumTracksData) {
        this.albumTracksData = albumTracksData;
    }

    @Override
    public String toString() {
        return "AlbumTracksAlbum{" +
                "albumTracksData=" + albumTracksData +
                '}';
    }
}
