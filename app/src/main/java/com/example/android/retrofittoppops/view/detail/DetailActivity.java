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
import com.example.android.retrofittoppops.commons.utils.Const;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

// TODO
// Scroll view is scrolled to bottom when activity is created, should be at the begging.
public class DetailActivity extends AppCompatActivity {

    public static void StartActivity(Activity activity, String trackName, String albumId, Integer songPosition) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(Const.Extras.TRACK_NAME, trackName);
        intent.putExtra(Const.Extras.ALBUM_ID, albumId);
        intent.putExtra(Const.Extras.SONG_POSITION, songPosition);
        activity.startActivity(intent);
    }

    DetailTrackAdapter adapter;
    protected DetailViewModel detailViewModel;

    @BindView(R.id.song_list_rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_detail_song_position)
    TextView songRank;
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
    Integer songPosition;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initializeToolbar(detailToolbar);
        getSupportActionBar().setTitle(null);

        Intent intent = getIntent();

        String albumId = intent.getStringExtra(Const.Extras.ALBUM_ID);
        trackName = intent.getStringExtra(Const.Extras.TRACK_NAME);
        songName.setText(trackName);
        songPosition = intent.getIntExtra(Const.Extras.SONG_POSITION, 0);
        songRank.setText(String.format(getString(R.string.detail_rank), String.valueOf(Integer.toString(songPosition))));


        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DetailTrackAdapter();
        recyclerView.setAdapter(adapter);

        detailViewModel.onError().observe(this, errorMessage -> {
            // TODO
            // Why are you no using errorMessage?
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage(R.string.error_message)
                    .setTitle(getString(R.string.error_dialog_title))
                    .setPositiveButton(R.string.button_ok, (dialog1, id) -> dialog1.dismiss())
                    .create();

            dialog.show();
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
            albumName.setText(albumEntity.getName());
        }
        if (!TextUtils.isEmpty(albumEntity.getArtistName())) {
            artistName.setText(albumEntity.getArtistName());
        }
        Picasso.get()
                .load(albumEntity.getCoverBig())
                .placeholder(R.drawable.placeholder)
                .into(albumCover);
    }

    private void initializeToolbar(Toolbar detailToolbar) {
        setSupportActionBar(detailToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            // TODO
            // Use AlertDialog in all error cases.
            Toast.makeText(this, "getSupportActionbar is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}