package com.example.android.retrofittoppops.view;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.model.Chart.ChartDataTracks;
import com.example.android.retrofittoppops.model.Chart.ChartTopPops;
import com.example.android.retrofittoppops.model.Chart.ChartTracks;
import com.example.android.retrofittoppops.rest.ApiClient;
import com.example.android.retrofittoppops.rest.ApiInterface;
import com.example.android.retrofittoppops.controller.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main_activity)
    RecyclerView rvView;
    RecyclerAdapter adapterMainRv;

    @BindView(R.id.toolbar)
    Toolbar myToolbar;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        adapterMainRv = new RecyclerAdapter();
        rvView.setAdapter(adapterMainRv);

        fetchCharts();

        pullToRefresh.setOnRefreshListener(() -> {
            fetchCharts();
            Toast.makeText(getApplicationContext(), "List refreshed", Toast.LENGTH_SHORT).show();
            pullToRefresh.setRefreshing(false);
        });
    }

    public void fetchCharts() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ChartTopPops> call = apiService.getTopPops();
        call.enqueue(new Callback<ChartTopPops>() {
            @Override
            public void onResponse(Call<ChartTopPops> call, Response<ChartTopPops> response) {
                ChartTracks chartTracks = response.body().getChartTracks();
                adapterMainRv.setDataTracksList(chartTracks.getChartDataTracksList());
            }

            @Override
            public void onFailure(Call<ChartTopPops> call, Throwable t) {
                Log.e("API error", "Error fetching charts.");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_normal: {
                ArrayList<ChartDataTracks> ascendingTracks = new ArrayList<>(adapterMainRv.getData());
                Collections.sort(ascendingTracks, (t1, t2) -> t1.getPosition() - t2.getPosition());

                adapterMainRv.setDataTracksList(ascendingTracks);
                Toast.makeText(getApplicationContext(), "Sorted as retrieved from API!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.sort_ascending: {
                ArrayList<ChartDataTracks> ascendingTracks = new ArrayList<>(adapterMainRv.getData());
                Collections.sort(ascendingTracks, (t1, t2) -> t1.getDuration() - t2.getDuration());

                adapterMainRv.setDataTracksList(ascendingTracks);
                Toast.makeText(getApplicationContext(), "Sorted ascending by track duration!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.sort_descending: {
                ArrayList<ChartDataTracks> descendingTracks = new ArrayList<>(adapterMainRv.getData());
                Collections.sort(descendingTracks, (t1, t2) -> t2.getDuration() - t1.getDuration());

                adapterMainRv.setDataTracksList(descendingTracks);
                Toast.makeText(getApplicationContext(), "Sorted descending by track duration!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
}
