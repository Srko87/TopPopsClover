package com.example.android.retrofittoppops.model;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;

public class TrackArtistHelper {

    public TrackEntity track;
    public ArtistEntity artist = null;

    public TrackArtistHelper(TrackEntity track) {this.track = track; }

    public TrackArtistHelper(TrackEntity track, ArtistEntity artist) {
        this.track = track;
        this.artist = artist;
    }

    public TrackArtistHelper () {}

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public void setTrack(TrackEntity track) {
        this.track = track;
    }

}
