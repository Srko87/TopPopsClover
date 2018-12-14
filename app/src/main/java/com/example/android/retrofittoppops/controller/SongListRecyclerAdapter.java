package com.example.android.retrofittoppops.controller;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.model.Album.AlbumTracksData;

import java.util.ArrayList;

public class SongListRecyclerAdapter extends RecyclerView.Adapter<SongListRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<AlbumTracksData> albumTracksDataArrayList;

    public SongListRecyclerAdapter(Context context, ArrayList<AlbumTracksData> albumTracksDataArrayList) {
        this.context = context;
        this.albumTracksDataArrayList = albumTracksDataArrayList;
    }

    @Override
    public SongListRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_rv_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongListRecyclerAdapter.MyViewHolder myViewHolder, int position) {
        myViewHolder.songName.setText(albumTracksDataArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (albumTracksDataArrayList != null) {
            return albumTracksDataArrayList.size();
        }
        return 0;
    }

    public void setAlbumTracksDataArrayList(ArrayList<AlbumTracksData> albumTracksDataArrayList) {
        this.albumTracksDataArrayList = albumTracksDataArrayList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.detail_song_name_row) TextView songName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

