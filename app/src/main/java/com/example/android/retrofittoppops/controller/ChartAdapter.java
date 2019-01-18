package com.example.android.retrofittoppops.controller;

import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.repository.TrackRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.model.TrackArtistHelper;
import com.example.android.retrofittoppops.utils.Tools;
import com.example.android.retrofittoppops.view.DetailActivity;
import com.example.android.retrofittoppops.viewmodel.TracksViewModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.MyviewHolder> implements TrackRepository.AsyncResponse {

    private List<TrackArtistHelper> data = new ArrayList<>();
    private TracksViewModel tracksViewModel;
    private ArtistCallback listener;
    private volatile HashMap<String, LoadState> artistQueryFlag = new HashMap<>();

    public enum LoadState {
        INIT,
        PROGRESS,
        DONE
    }

    public ChartAdapter(ArtistCallback listener) {
        this.listener = listener;
    }

    public void updateItems(List<TrackArtistHelper> list) {
        data.clear();
        data.addAll(list);
        for (TrackArtistHelper item : data) {
            if (item.track.getArtistId() != null){
                artistQueryFlag.put(item.track.getArtistId(), LoadState.INIT);
            }
        }
        notifyDataSetChanged();
    }
    public void updateArtist(ArtistEntity artistEntity) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).track.getArtistId().equals(artistEntity.getId())){
                data.get(i).setArtist(artistEntity);
                artistQueryFlag.put(data.get(i).track.getArtistId(), LoadState.DONE);
                notifyDataSetChanged();
            }
        }

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

    @Override
    public void queryFinish(ArtistEntity artistEntity, int position) {
        data.get(position).artist = artistEntity;
        notifyItemChanged(position);
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
            songName.setText("Song name: " + item.track.getTitle());
            songDuration.setText(String.valueOf("Song Duration: " + Tools.secondsToString(item.track.getDuration())));

//            Not sure if this is good
            if (item.track.getArtistId() != null) {
                if (item.artist == null && artistQueryFlag.get(item.track.getArtistId()) == LoadState.INIT) {
                    artistQueryFlag.put(item.track.getArtistId(), LoadState.PROGRESS);
                    listener.onArtistEmpty(item.track.getArtistId());
                } else if (item.artist != null && artistQueryFlag.get(item.track.getArtistId()) == LoadState.DONE){
                    artistName.setText(item.artist.getName());
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO Handle recycler on click
                    //   DetailActivity.StartActivity((Activity)view.getContext(), item);
                }
            });
        }
    }
    public interface ArtistCallback {
        void onArtistEmpty(String id);
    }
}