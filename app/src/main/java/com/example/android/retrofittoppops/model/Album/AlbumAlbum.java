package com.example.android.retrofittoppops.model.Album;

import com.example.android.retrofittoppops.commons.baseClasses.BaseResponse;
import com.example.android.retrofittoppops.model.ErrorResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class AlbumAlbum extends BaseResponse implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("upc")
    private String upc;
    @SerializedName("link")
    private String link;
    @SerializedName("share")
    private String share;
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
    @SerializedName("genre_id")
    private Integer genreId;
    @SerializedName("albumGenres")
    private AlbumGenres albumGenres;
    @SerializedName("label")
    private String label;
    @SerializedName("nb_tracks")
    private Integer nbTracks;
    @SerializedName("duration")
    private Integer duration;
    @SerializedName("fans")
    private Integer fans;
    @SerializedName("rating")
    private Integer rating;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("record_type")
    private String recordType;
    @SerializedName("available")
    private Boolean available;
    @SerializedName("tracklist")
    private String tracklist;
    @SerializedName("explicit_lyrics")
    private Boolean explicitLyrics;
    @SerializedName("contributors")
    private ArrayList<AlbumContributors> contributors;
    @SerializedName("artist")
    private AlbumArtist albumArtist;
    @SerializedName("type")
    private String type;
    @SerializedName("tracks")
    private AlbumTracksAlbum tracks;

    public AlbumAlbum(ErrorResponse errorResponse,String id, String title, String upc, String link, String share, String cover, String coverSmall, String coverMedium, String coverBig, String coverXl, Integer genreId, AlbumGenres albumGenres, String label, Integer nbTracks, Integer duration, Integer fans, Integer rating, String releaseDate, String recordType, Boolean available, String tracklist, Boolean explicitLyrics, AlbumArtist albumArtist, String type, AlbumTracksAlbum tracks) {
        super(errorResponse);
        this.id = id;
        this.title = title;
        this.upc = upc;
        this.link = link;
        this.share = share;
        this.cover = cover;
        this.coverSmall = coverSmall;
        this.coverMedium = coverMedium;
        this.coverBig = coverBig;
        this.coverXl = coverXl;
        this.genreId = genreId;
        this.albumGenres = albumGenres;
        this.label = label;
        this.nbTracks = nbTracks;
        this.duration = duration;
        this.fans = fans;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.recordType = recordType;
        this.available = available;
        this.tracklist = tracklist;
        this.explicitLyrics = explicitLyrics;
        this.albumArtist = albumArtist;
        this.type = type;
        this.tracks = tracks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
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

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public AlbumGenres getAlbumGenres() {
        return albumGenres;
    }

    public void setAlbumGenres(AlbumGenres albumGenres) {
        this.albumGenres = albumGenres;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getNbTracks() {
        return nbTracks;
    }

    public void setNbTracks(Integer nbTracks) {
        this.nbTracks = nbTracks;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public Boolean getExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(Boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    public ArrayList<AlbumContributors> getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<AlbumContributors> contributors) {
        this.contributors = contributors;
    }

    public AlbumArtist getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(AlbumArtist albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AlbumTracksAlbum getTracks() {
        return tracks;
    }

    public void setTracks(AlbumTracksAlbum tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "AlbumAlbum{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", upc='" + upc + '\'' +
                ", link='" + link + '\'' +
                ", share='" + share + '\'' +
                ", cover='" + cover + '\'' +
                ", coverSmall='" + coverSmall + '\'' +
                ", coverMedium='" + coverMedium + '\'' +
                ", coverBig='" + coverBig + '\'' +
                ", coverXl='" + coverXl + '\'' +
                ", genreId=" + genreId +
                ", albumGenres=" + albumGenres +
                ", label='" + label + '\'' +
                ", nbTracks=" + nbTracks +
                ", duration=" + duration +
                ", fans=" + fans +
                ", rating=" + rating +
                ", releaseDate='" + releaseDate + '\'' +
                ", recordType='" + recordType + '\'' +
                ", available=" + available +
                ", tracklist='" + tracklist + '\'' +
                ", explicitLyrics=" + explicitLyrics +
                ", contributors=" + contributors +
                ", albumArtist=" + albumArtist +
                ", type='" + type + '\'' +
                ", tracks=" + tracks +
                '}';
    }
}
