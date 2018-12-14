package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ChartPlaylists implements Serializable {

    private int total;

    @SerializedName("data")
    private ArrayList<ChartDataPlaylists> chartDataPlaylistsList;

    public ArrayList<ChartDataPlaylists> getChartDataPlaylistsList() {
        return chartDataPlaylistsList;
    }

    public void setChartDataPlaylistsList(ArrayList<ChartDataPlaylists> chartDataPlaylistsList) {
        this.chartDataPlaylistsList = chartDataPlaylistsList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ChartPlaylists{" +
                "total=" + total +
                ", chartDataPlaylistsList=" + chartDataPlaylistsList +
                '}';
    }
}
