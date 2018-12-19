package com.example.android.retrofittoppops.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.controller.SongListRecyclerAdapter;
import com.example.android.retrofittoppops.model.Album.AlbumAlbum;
import com.example.android.retrofittoppops.model.Album.AlbumTracksData;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.rest.ApiClient;
import com.example.android.retrofittoppops.rest.ApiInterface;
import com.example.android.retrofittoppops.utils.Const;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static void StartActivity(Activity activity, int id){
        StartActivity(activity, null, id);
    }
    public static void StartActivity(Activity activity, ChartDataTracks data){
        StartActivity(activity, data, -1);
    }

    private static void StartActivity(Activity activity, ChartDataTracks data, int id){
        Intent intent = new Intent(activity, DetailActivity.class);
        if(data!= null){
            intent.putExtra(Const.Extras.TRACK_OBJECT,data);
        } else {
            // add id param
        }
        activity.startActivity(intent);
    }

    ChartDataTracks track;
    ArrayList<AlbumTracksData> albumTracksData;
    SongListRecyclerAdapter recyclerAdapter;

    @BindView(R.id.song_list_rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_detail_song_name)
    TextView songName;
    @BindView(R.id.tv_detail_album_name)
    TextView albumName;
    @BindView(R.id.tv_detail_artist_name)
    TextView artistName;
    @BindView(R.id.iv_album_cover)
    ImageView albumCover;
    @BindView(R.id.detail_toolbar)
    Toolbar detailToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initializeToolbar(detailToolbar);

        Intent intent = getIntent();
        track = (ChartDataTracks) intent.getExtras().getSerializable(Const.Extras.TRACK_OBJECT);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new SongListRecyclerAdapter(getApplicationContext(), albumTracksData);
        recyclerView.setAdapter(recyclerAdapter);

        fetchAlbum();
    }

    private void fetchAlbum() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AlbumAlbum> call = apiService.getAlbum(track.getChartAlbumTracks().getId());
        call.enqueue(new Callback<AlbumAlbum>() {
            @Override
            public void onResponse(Call<AlbumAlbum> call, Response<AlbumAlbum> response) {
                displayAlbumDescription(response.body());
                albumTracksData = response.body().getTracks().getAlbumTracksData();
                recyclerAdapter.setAlbumTracksDataArrayList(albumTracksData);
            }

            @Override
            public void onFailure(Call<AlbumAlbum> call, Throwable t) {
            }
        });
    }

    private void displayAlbumDescription(AlbumAlbum albumAlbum) {

        songName.setText(getString(R.string.song_name_detail) + track.getTitle());
        albumName.setText(getString(R.string.album_name_detail) + albumAlbum.getTitle());
        artistName.setText(getString(R.string.artist_name_detail) + albumAlbum.getAlbumArtist().getName());
        Picasso.get().load(albumAlbum.getCoverBig()).into(albumCover);
    }

    private void initializeToolbar (Toolbar detailToolbar) {
        setSupportActionBar(detailToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            Toast.makeText(this, "getSupportActionbar is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}