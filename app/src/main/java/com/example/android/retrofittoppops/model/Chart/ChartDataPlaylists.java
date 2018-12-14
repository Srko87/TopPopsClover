package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ChartDataPlaylists implements Serializable {
    @SerializedName("id")
    private Double id;
    @SerializedName("title")
    private String title;
    @SerializedName("public")
    private Boolean _public;
    @SerializedName("nb_tracks")
    private Integer nbTracks;
    @SerializedName("link")
    private String link;
    @SerializedName("picture")
    private String picture;
    @SerializedName("picture_small")
    private String pictureSmall;
    @SerializedName("picture_medium")
    private String pictureMedium;
    @SerializedName("picture_big")
    private String pictureBig;
    @SerializedName("picture_xl")
    private String pictureXl;
    @SerializedName("checksum")
    private String checksum;
    @SerializedName("tracklist")
    private String tracklist;
    @SerializedName("creation_date")
    private String creationDate;
    @SerializedName("chartUser")
    private ChartUser chartUser;
    @SerializedName("type")
    private String type;

    ChartDataPlaylists(Double id, String title, Boolean aPublic, Integer nbTracks, String link, String picture, String pictureSmall, String pictureMedium, String pictureBig, String pictureXl, String checksum, String tracklist, String creationDate, ChartUser chartUser, String type) {
        this.id = id;
        this.title = title;
        _public = aPublic;
        this.nbTracks = nbTracks;
        this.link = link;
        this.picture = picture;
        this.pictureSmall = pictureSmall;
        this.pictureMedium = pictureMedium;
        this.pictureBig = pictureBig;
        this.pictureXl = pictureXl;
        this.checksum = checksum;
        this.tracklist = tracklist;
        this.creationDate = creationDate;
        this.chartUser = chartUser;
        this.type = type;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean get_public() {
        return _public;
    }

    public void set_public(Boolean _public) {
        this._public = _public;
    }

    public Integer getNbTracks() {
        return nbTracks;
    }

    public void setNbTracks(Integer nbTracks) {
        this.nbTracks = nbTracks;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(String pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public String getPictureMedium() {
        return pictureMedium;
    }

    public void setPictureMedium(String pictureMedium) {
        this.pictureMedium = pictureMedium;
    }

    public String getPictureBig() {
        return pictureBig;
    }

    public void setPictureBig(String pictureBig) {
        this.pictureBig = pictureBig;
    }

    public String getPictureXl() {
        return pictureXl;
    }

    public void setPictureXl(String pictureXl) {
        this.pictureXl = pictureXl;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public ChartUser getUserList() {
        return chartUser;
    }

    public void setUserList(ChartUser chartUserList) {
        this.chartUser = chartUserList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChartDataPlaylists{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", _public=" + _public +
                ", nbTracks=" + nbTracks +
                ", link='" + link + '\'' +
                ", picture='" + picture + '\'' +
                ", pictureSmall='" + pictureSmall + '\'' +
                ", pictureMedium='" + pictureMedium + '\'' +
                ", pictureBig='" + pictureBig + '\'' +
                ", pictureXl='" + pictureXl + '\'' +
                ", checksum='" + checksum + '\'' +
                ", tracklist='" + tracklist + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", userList=" + chartUser +
                ", type='" + type + '\'' +
                '}';
    }
}

