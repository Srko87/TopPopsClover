package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChartTopPops implements Serializable  {

    @SerializedName("tracks")
    private ChartTracks chartTracks;
    public ChartTracks getChartTracks() { return chartTracks; }

    public void setTracksList (ChartTracks chartTracks) {
        this.chartTracks = chartTracks;
    }

    @SerializedName("albums")
    private ChartAlbums chartAlbums;
    public ChartAlbums getChartAlbums() { return chartAlbums; }

    public void setChartAlbums(ChartAlbums chartAlbums) {
        this.chartAlbums = chartAlbums;
    }

    @SerializedName("artists")
    private ChartArtists chartArtists;
    public ChartArtists getChartArtists() { return chartArtists; }

    public void setChartArtists(ChartArtists chartArtists) {
        this.chartArtists = chartArtists;
    }

    @SerializedName("playlists")
    private ChartPlaylists chartPlaylists;
    public ChartPlaylists getChartPlaylists() { return chartPlaylists; }

    public void setChartPlaylists(ChartPlaylists chartPlaylists) {
        this.chartPlaylists = chartPlaylists;
    }

    @Override
    public String toString() {
        return "ChartTopPops{" +
                "tracksList=" + chartTracks +
                ", albumsList=" + chartAlbums +
                ", artistsList=" + chartArtists +
                ", playlistsList=" + chartPlaylists +
                '}';
    }
}

