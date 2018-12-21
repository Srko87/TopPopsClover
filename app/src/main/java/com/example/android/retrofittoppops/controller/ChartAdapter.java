package com.example.android.retrofittoppops.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.model.TrackArtistHelper;
import com.example.android.retrofittoppops.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.MyviewHolder> {

    private List<TrackArtistHelper> data = new ArrayList<>();

    public ChartAdapter() {
    }


    public void updateItems(List<TrackArtistHelper> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public List<TrackArtistHelper> getData() {
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
        @BindView(R.id.song_position_tv)
        TextView songPosition;
        @BindView(R.id.song_name_tv)
        TextView songName;
        @BindView(R.id.song_length_tv)
        TextView songDuration;
        @BindView(R.id.artist_name_tv)
        TextView artistName;

        public MyviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(final int position) {

            TrackArtistHelper item = data.get(position);

            songPosition.setText(String.valueOf("Song position: " + item.track.getPosition()));
            songName.setText(String.format("Song name: %s", item.track.getTitle()));

            if(item.artist!= null){
                artistName.setText(String.format("Artist name: %s", item.artist.getName()));
            } else {
                // TODO
                // start lazy loading from local db
                // on new thread
                // which item needs artist
                // artistDao - get artist via FK from track
                // find by track id, iterate through data set of rv
                // tiem.artist = result from query
            }

            songDuration.setText(String.valueOf("Song Duration: " + Tools.secondsToString(item.track.getDuration())));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //   DetailActivity.StartActivity((Activity)view.getContext(), item);
                }
            });
        }
    }
}