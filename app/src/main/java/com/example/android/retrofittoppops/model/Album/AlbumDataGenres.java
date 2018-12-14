package com.example.android.retrofittoppops.model.Album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlbumDataGenres implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("picture")
    private String picture;
    @SerializedName("type")
    private String type;

    AlbumDataGenres(Integer id, String name, String picture, String type) {
        this.id = id;
        this.name = name;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AlbumDataGenres{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
