package com.example.android.retrofittoppops.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.adapter.DetailTrackAdapter;
import com.example.android.retrofittoppops.database.entity.AlbumEntity;
import com.example.android.retrofittoppops.utils.Const;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static void StartActivity(Activity activity, String trackName, String albumId) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(Const.Extras.TRACK_NAME, trackName);
        intent.putExtra(Const.Extras.ALBUM_ID, albumId);
        activity.startActivity(intent);
    }

    DetailTrackAdapter adapter;
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
        songName.setText(String.format(getString(R.string.song_name_detail), trackName));

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DetailTrackAdapter();
        recyclerView.setAdapter(adapter);

        detailViewModel.onError().observe(this, errorMessage -> {
            // TODO
            // only one type of error is shown here
            // implement other error types
            Toast.makeText(this, "No internet, please try again", Toast.LENGTH_SHORT).show();
        });

        detailViewModel.getAlbumLiveData(albumId).observe(this, albumEntity -> {
            if (albumEntity != null) {
                displayAlbumDescription(albumEntity);
                adapter.setData(albumEntity.getTrackList());
            }
        });

        detailViewModel.fetchAlbumFromApi(albumId);
    }

    private void displayAlbumDescription(AlbumEntity albumEntity) {
        if (!TextUtils.isEmpty(albumEntity.getName())) {
            albumName.setText(String.format(getString(R.string.album_name_detail), albumEntity.getName()));
        }
        if (!TextUtils.isEmpty(albumEntity.getArtistName())) {
            artistName.setText(String.format(getString(R.string.artist_name_detail), albumEntity.getArtistName()));
        }
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