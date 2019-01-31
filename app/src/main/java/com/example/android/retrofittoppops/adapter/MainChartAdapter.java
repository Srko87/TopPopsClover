package com.example.android.retrofittoppops.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.model.TrackArtistHelper;
import com.example.android.retrofittoppops.utils.Tools;
import com.example.android.retrofittoppops.view.detail.DetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainChartAdapter extends RecyclerView.Adapter<MainChartAdapter.MyviewHolder> {

    private List<TrackArtistHelper> data = new ArrayList<>();

    public void updateItems(List<TrackArtistHelper> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void sortByPosition() {
        Collections.sort(data, (t1, t2) -> t1.track.getPosition() - t2.track.getPosition());
        notifyDataSetChanged();
    }

    public void sortByDurationAsc() {
        Collections.sort(data, (t1, t2) -> t1.track.getDuration() - t2.track.getDuration());
        notifyDataSetChanged();
    }

    public void sortByDurationDesc() {
        Collections.sort(data, (t1, t2) -> t2.track.getDuration() - t1.track.getDuration());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_rv_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class MyviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.song_position_tv)
        TextView songPositionTv;
        @BindView(R.id.song_name_tv)
        TextView songNameTv;
        @BindView(R.id.song_length_tv)
        TextView songDurationTv;
        @BindView(R.id.artist_name_tv)
        TextView artistNameTv;

        MyviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(final int position) {
            TrackArtistHelper item = data.get(position);
            
            Context context = songPositionTv.getContext();
            String songPositionText = context.getString(R.string.song_position_main, item.track.getPosition());
            songPositionTv.setText(songPositionText);
            
            String songNameText = context.getString(R.string.song_name_detail, item.track.getTitle());
            songNameTv.setText(songNameText);
            
            String songDurationText = context.getString(R.string.song_duration_main, Tools.secondsToString(item.track.getDuration()));
            songDurationTv.setText(songDurationText);
            
            if (item.artist != null) {

                String artistNameText = context.getString(R.string.artist_name_main, item.artist.getName());
                artistNameTv.setText(artistNameText);
            }

            itemView.setOnClickListener(view -> DetailActivity.StartActivity((Activity) view.getContext(), item.track.getTitle(), item.track.getAlbumId()));
        }
    }

}