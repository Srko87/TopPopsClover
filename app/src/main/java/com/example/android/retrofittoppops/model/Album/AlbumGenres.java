package com.example.android.retrofittoppops.model.Album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AlbumGenres implements Serializable {

    @SerializedName("data")
    private ArrayList<AlbumDataGenres> albumDataGenres;

    public ArrayList<AlbumDataGenres> getAlbumDataGenres() {
        return albumDataGenres;
    }

    public void setAlbumDataGenres(ArrayList<AlbumDataGenres> albumDataGenres) {
        this.albumDataGenres = albumDataGenres;
    }
}
