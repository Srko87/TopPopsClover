package com.example.android.retrofittoppops.network;

import com.example.android.retrofittoppops.model.Album.AlbumAlbum;
import com.example.android.retrofittoppops.model.Chart.ChartTopPops;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("chart")
    Single<ChartTopPops> getTopPops();

    @GET("album/{album_id}")
    Single<AlbumAlbum> getAlbum(@Path("album_id") String album_id);
}