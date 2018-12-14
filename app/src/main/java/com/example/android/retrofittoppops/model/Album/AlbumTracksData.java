package com.example.android.retrofittoppops.model.Album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlbumTracksData implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("readable")
    private Boolean readable;
    @SerializedName("title")
    private String title;
    @SerializedName("title_short")
    private String titleShort;
    @SerializedName("title_version")
    private String titleVersion;
    @SerializedName("link")
    private String link;
    @SerializedName("duration")
    private Integer duration;
    @SerializedName("rank")
    private Integer rank;
    @SerializedName("explicit_lyrics")
    private Boolean explicitLyrics;
    @SerializedName("preview")
    private String preview;
    @SerializedName("artist")
    private AlbumTracksDataArtist albumTracksDataArtist;
    @SerializedName("type")
    private String type;

    AlbumTracksData(Integer id, Boolean readable, String title, String titleShort, String titleVersion, String link, Integer duration, Integer rank, Boolean explicitLyrics, String preview, AlbumTracksDataArtist albumTracksDataArtist, String type) {
        this.id = id;
        this.readable = readable;
        this.title = title;
        this.titleShort = titleShort;
        this.titleVersion = titleVersion;
        this.link = link;
        this.duration = duration;
        this.rank = rank;
        this.explicitLyrics = explicitLyrics;
        this.preview = preview;
        this.albumTracksDataArtist = albumTracksDataArtist;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getReadable() {
        return readable;
    }

    public void setReadable(Boolean readable) {
        this.readable = readable;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(Boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public AlbumTracksDataArtist getAlbumTracksDataArtist() {
        return albumTracksDataArtist;
    }

    public void setAlbumTracksDataArtist(AlbumTracksDataArtist albumTracksDataArtist) {
        this.albumTracksDataArtist = albumTracksDataArtist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AlbumTracksData{" +
                "id=" + id +
                ", readable=" + readable +
                ", title='" + title + '\'' +
                ", titleShort='" + titleShort + '\'' +
                ", titleVersion='" + titleVersion + '\'' +
                ", link='" + link + '\'' +
                ", duration=" + duration +
                ", rank=" + rank +
                ", explicitLyrics=" + explicitLyrics +
                ", preview='" + preview + '\'' +
                ", albumTracksDataArtist=" + albumTracksDataArtist +
                ", type='" + type + '\'' +
                '}';
    }
}

