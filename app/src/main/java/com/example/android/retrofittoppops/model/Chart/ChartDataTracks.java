package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChartDataTracks implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("title_short")
    private String titleShort;
    @SerializedName("title_version")
    private String titleVersion;
    @SerializedName("link")
    private String link;
    @SerializedName("duration")
    private int duration;
    @SerializedName("rank")
    private int rank;
    @SerializedName("explicit_lyrics")
    private boolean explicitLyrics;
    @SerializedName("preview")
    private String preview;
    @SerializedName("position")
    private int position;
    @SerializedName("artist")
    private ChartArtistTracks chartArtistTracks;
    @SerializedName("album")
    private ChartAlbumTracks chartAlbumTracks;
    @SerializedName("type")
    private String type;

    ChartDataTracks(int id, String title, String titleShort, String titleVersion, String link, int duration, int rank, boolean explicitLyrics, String preview, int position, ChartArtistTracks chartArtistTracks, ChartAlbumTracks chartAlbumTracks, String type) {
        this.id = id;
        this.title = title;
        this.titleShort = titleShort;
        this.titleVersion = titleVersion;
        this.link = link;
        this.duration = duration;
        this.rank = rank;
        this.explicitLyrics = explicitLyrics;
        this.preview = preview;
        this.position = position;
        this.chartArtistTracks = chartArtistTracks;
        this.chartAlbumTracks = chartAlbumTracks;
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

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public String getTitleVersion() {
        return titleVersion;
    }

    public void setTitleVersion(String titleVersion) {
        this.titleVersion = titleVersion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ChartArtistTracks getChartArtistTracks() {
        return chartArtistTracks;
    }

    public void setChartArtistTracks(ChartArtistTracks chartArtistTracks) {
        this.chartArtistTracks = chartArtistTracks;
    }

    public ChartAlbumTracks getChartAlbumTracks() {
        return chartAlbumTracks;
    }

    public void setChartAlbumTracks(ChartAlbumTracks chartAlbumTracks) {
        this.chartAlbumTracks = chartAlbumTracks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChartDataTracks{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titleShort='" + titleShort + '\'' +
                ", titleVersion='" + titleVersion + '\'' +
                ", link='" + link + '\'' +
                ", duration=" + duration +
                ", rank=" + rank +
                ", explicitLyrics=" + explicitLyrics +
                ", preview='" + preview + '\'' +
                ", position=" + position +
                ", chartArtistTracks=" + chartArtistTracks +
                ", chartAlbumTracks=" + chartAlbumTracks +
                ", type='" + type + '\'' +
                '}';
    }
}
