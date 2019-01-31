package com.example.android.retrofittoppops.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofittoppops.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

// TODO check code
public class DetailTrackAdapter extends RecyclerView.Adapter<DetailTrackAdapter.MyViewHolder> {

    private List<String> data = new ArrayList<>();

    public DetailTrackAdapter() {
    }

    public void setData(List<String> newData) {
        if (newData != null && !newData.isEmpty()) {
            data.clear();
            data.addAll(newData);
            notifyDataSetChanged();
        }
    }

    @Override
    public DetailTrackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_rv_row, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailTrackAdapter.MyViewHolder myViewHolder, int position) {
        myViewHolder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.detail_song_name_row)
        TextView songName;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position) {
            String item = data.get(position);
            songName.setText(item);
        }
    }
}

