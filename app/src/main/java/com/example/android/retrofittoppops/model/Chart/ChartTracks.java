package com.example.android.retrofittoppops.model.Chart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ChartTracks implements Serializable {

    private int total;

    @SerializedName("data")
    private ArrayList<ChartDataTracks> chartDataTracksList;

    public ArrayList<ChartDataTracks> getChartDataTracksList() {
        return chartDataTracksList;
    }

    public void setChartDataTracksList(ArrayList<ChartDataTracks> chartDataTracksList) {
        this.chartDataTracksList = chartDataTracksList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "ChartTracks{" +
                "total=" + total +
                ", chartDataTracksList=" + chartDataTracksList +
                '}';
    }
}

