package com.example.android.retrofittoppops.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.controller.SongListRecyclerAdapter;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;

import com.example.android.retrofittoppops.utils.Const;
import com.example.android.retrofittoppops.viewmodel.DetailViewModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static void StartActivity(Activity activity, String trackName, String albumId) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(Const.Extras.TRACK_NAME, trackName);
        intent.putExtra(Const.Extras.ALBUM_ID, albumId);
        activity.startActivity(intent);
    }

    SongListRecyclerAdapter recyclerAdapter;
    private DetailViewModel detailViewModel;

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
    String trackName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initializeToolbar(detailToolbar);

        Intent intent = getIntent();

        String albumId = intent.getStringExtra(Const.Extras.ALBUM_ID);
        trackName = intent.getStringExtra(Const.Extras.TRACK_NAME);

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        detailViewModel.getAlbumLiveData(albumId).observe(this, albumEntity -> {

            if (albumEntity.getTrackList() == null || albumEntity.getCover() == null) {
                detailViewModel.fetchAlbumFromApi(albumId);
                displayAlbumDescription(albumEntity);
            } else {
                displayAlbumDescription(albumEntity);
                recyclerAdapter = new SongListRecyclerAdapter(this, albumEntity.getTrackList());
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
    }

    private void displayAlbumDescription(AlbumEntity albumEntity) {
        songName.setText(String.format(getString(R.string.song_name_detail),trackName));
        albumName.setText(String.format(getString(R.string.album_name_detail), albumEntity.getName()));
        artistName.setText(String.format(getString(R.string.artist_name_detail), albumEntity.getArtistName()));
        Picasso.get().load(albumEntity.getCover()).into(albumCover);
    }

    private void initializeToolbar(Toolbar detailToolbar) {
        setSupportActionBar(detailToolbar);
        if (getSupportActionBar() != null) {
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