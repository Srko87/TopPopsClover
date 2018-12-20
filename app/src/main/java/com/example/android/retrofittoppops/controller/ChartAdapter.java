package com.example.android.retrofittoppops.controller;

import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.utils.Tools;
import com.example.android.retrofittoppops.view.DetailActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.MyviewHolder> {

    private List<TrackEntity> data = new ArrayList<>();

    public ChartAdapter() {}


    public void updateItems(List<TrackEntity> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public List<TrackEntity> getData(){
        return data;
    }

    @Override
    public ChartAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_rv_row, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChartAdapter.MyviewHolder holder, final int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.song_position_tv) TextView songPosition;
        @BindView(R.id.song_name_tv) TextView songName;
        @BindView(R.id.song_length_tv) TextView songDuration;

        public MyviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(final int position){
            final TrackEntity item = data.get(position);

            songPosition.setText(String.valueOf("Song position: " + item.getPosition()));
            songName.setText("Song name: " + item.getTitle());
            songDuration.setText(String.valueOf("Song Duration: " + Tools.secondsToString(item.getDuration())));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   DetailActivity.StartActivity((Activity)view.getContext(), item);
                }
            });
        }
    }
}