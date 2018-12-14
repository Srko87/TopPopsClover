package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ChartAlbums implements Serializable {

    private int total;

    @SerializedName("data")
    private ArrayList<ChartDataAlbums> chartDataAlbumsList;

    public ArrayList<ChartDataAlbums> getChartDataAlbumsList() {
        return chartDataAlbumsList;
    }

    public void setChartDataAlbumsList(ArrayList<ChartDataAlbums> chartDataAlbumsList) {
        this.chartDataAlbumsList = chartDataAlbumsList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ChartAlbums{" +
                "total=" + total +
                ", chartDataAlbumsList=" + chartDataAlbumsList +
                '}';
    }
}