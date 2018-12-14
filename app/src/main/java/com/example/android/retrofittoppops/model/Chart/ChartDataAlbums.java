package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ChartDataAlbums implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("cover")
    private String cover;
    @SerializedName("cover_small")
    private String coverSmall;
    @SerializedName("cover_medium")
    private String coverMedium;
    @SerializedName("cover_big")
    private String coverBig;
    @SerializedName("cover_xl")
    private String coverXl;
    @SerializedName("record_type")
    private String recordType;
    @SerializedName("tracklist")
    private String tracklist;
    @SerializedName("explicit_lyrics")
    private boolean explicitLyrics;
    @SerializedName("position")
    private int position;
    @SerializedName("artist")
    private ChartArtistAlbums chartArtistAlbums;
    @SerializedName("type")
    private String type;

    ChartDataAlbums(int id, String title, String cover, String coverSmall, String coverMedium, String coverBig, String coverXl, String recordType, String tracklist, boolean explicitLyrics, int position, ChartArtistAlbums chartArtistAlbums, String type) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.coverSmall = coverSmall;
        this.coverMedium = coverMedium;
        this.coverBig = coverBig;
        this.coverXl = coverXl;
        this.recordType = recordType;
        this.tracklist = tracklist;
        this.explicitLyrics = explicitLyrics;
        this.position = position;
        this.chartArtistAlbums = chartArtistAlbums;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    public String getCoverBig() {
        return coverBig;
    }

    public void setCoverBig(String coverBig) {
        this.coverBig = coverBig;
    }

    public String getCoverXl() {
        return coverXl;
    }

    public void setCoverXl(String coverXl) {
        this.coverXl = coverXl;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public boolean isExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ChartArtistAlbums getChartArtistAlbums() {
        return chartArtistAlbums;
    }

    public void setChartArtistAlbums(ChartArtistAlbums chartArtistAlbums) {
        this.chartArtistAlbums = chartArtistAlbums;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChartDataAlbums{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", coverSmall='" + coverSmall + '\'' +
                ", coverMedium='" + coverMedium + '\'' +
                ", coverBig='" + coverBig + '\'' +
                ", coverXl='" + coverXl + '\'' +
                ", recordType='" + recordType + '\'' +
                ", tracklist='" + tracklist + '\'' +
                ", explicitLyrics=" + explicitLyrics +
                ", position=" + position +
                ", chartArtistAlbums=" + chartArtistAlbums +
                ", type='" + type + '\'' +
                '}';
    }
}

