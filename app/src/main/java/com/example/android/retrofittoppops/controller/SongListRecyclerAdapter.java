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

import java.util.List;

public class SongListRecyclerAdapter extends RecyclerView.Adapter<SongListRecyclerAdapter.MyViewHolder> {

    Context context;
    List<String> albumTracklist;

    public SongListRecyclerAdapter(Context context, List<String> albumTracklist) {
        this.context = context;
        this.albumTracklist = albumTracklist;
    }

    @Override
    public SongListRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_rv_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongListRecyclerAdapter.MyViewHolder myViewHolder, int position) {
        myViewHolder.songName.setText(albumTracklist.get(position));
    }

    @Override
    public int getItemCount() {
        if (albumTracklist != null) {
            return albumTracklist.size();
        }
        return 0;
    }

    public void setAlbumTracklist(List<String> albumTracklist) {
        this.albumTracklist = albumTracklist;
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

