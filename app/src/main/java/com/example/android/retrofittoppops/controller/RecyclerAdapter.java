package com.example.android.retrofittoppops.controller;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofittoppops.utils.Tools;
import com.example.android.retrofittoppops.view.DetailActivity;
import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    private List<ChartDataTracks> data = new ArrayList<>();

    public RecyclerAdapter() {}

    public void setDataTracksList(List<ChartDataTracks> newData) {
        if(newData != null){
            this.data = newData;
            notifyDataSetChanged();
        }
    }

    public List<ChartDataTracks> getData(){
        return data;
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_rv_row, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, final int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.song_position_tv) TextView songPosition;
        @BindView(R.id.song_name_tv) TextView songName;
        @BindView(R.id.artist_name_tv) TextView artistName;
        @BindView(R.id.song_length_tv) TextView songDuration;

        public MyviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(final int position){
            final ChartDataTracks item = data.get(position);

            songPosition.setText(String.valueOf("Song position: " + item.getPosition()));
            songName.setText("Song name: " + item.getTitle());
            artistName.setText("AlbumArtist name: " + item.getChartArtistTracks().getName());
            songDuration.setText(String.valueOf("Song Duration: " + Tools.secondsToString(item.getDuration())));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailActivity.StartActivity((Activity)view.getContext(), item);
                }
            });
        }
    }
}
