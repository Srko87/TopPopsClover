package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ChartArtists implements Serializable {

    private int total;

    @SerializedName("data")
    private ArrayList<ChartDataArtists> chartDataArtistsList;

    public ArrayList<ChartDataArtists> getChartDataArtistsList() {
        return chartDataArtistsList;
    }

    public void setChartDataArtistsList(ArrayList<ChartDataArtists> chartDataArtistsList) {
        this.chartDataArtistsList = chartDataArtistsList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ChartArtists{" +
                "total=" + total +
                ", chartDataArtistsList=" + chartDataArtistsList +
                '}';
    }
}


