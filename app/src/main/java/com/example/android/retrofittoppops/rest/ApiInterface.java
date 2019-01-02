package com.example.android.retrofittoppops.rest;

import com.example.android.retrofittoppops.model.Album.AlbumAlbum;
import com.example.android.retrofittoppops.model.Chart.ChartTopPops;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("chart")
    Call<ChartTopPops> getTopPops();

    @GET("album/{album_id}")
    Call<AlbumAlbum> getAlbum(@Path("album_id") String album_id);

}