package com.example.android.retrofittoppops.model;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;

public class TrackArtistHelper {
    public TrackEntity track;
    public ArtistEntity artist;

    public TrackArtistHelper(TrackEntity track, ArtistEntity artist) {
        this.track = track;
        this.artist = artist;
    }
}
