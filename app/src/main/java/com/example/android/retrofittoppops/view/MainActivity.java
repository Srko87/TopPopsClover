package com.example.android.retrofittoppops.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.controller.ChartAdapter;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.model.TrackArtistHelper;

import com.example.android.retrofittoppops.rest.ApiService;
import com.example.android.retrofittoppops.viewmodel.TracksViewModel;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main_activity)
    RecyclerView rvView;

    @BindView(R.id.toolbar)
    Toolbar myToolbar;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout pullToRefresh;

    private ChartAdapter adapterMainRv;
    private TracksViewModel tracksViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
        tracksViewModel = ViewModelProviders.of(this).get(TracksViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        adapterMainRv = new ChartAdapter(tracksViewModel);
        rvView.setAdapter(adapterMainRv);


        tracksViewModel.getAllTracks().observe(this, data -> {
            if (data != null && data.size() != 0) {
                tvNoData.setVisibility(View.GONE);

                List<TrackArtistHelper> adapterData = new ArrayList<>();

                for (TrackEntity item : data) {
                    adapterData.add(new TrackArtistHelper(item));
                }

                adapterMainRv.updateItems(adapterData);
                rvView.setVisibility(View.VISIBLE);
            } else {
                rvView.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        });

        pullToRefresh.setOnRefreshListener(() -> {

            tracksViewModel.fetchCharts();

            Toast.makeText(getApplicationContext(), "List refreshed", Toast.LENGTH_SHORT).show();
            pullToRefresh.setRefreshing(false);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //TODO Menu items
    // dev new todo
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_normal: {
//                  ArrayList<ChartDataTracks> ascendingTracks = new ArrayList<>(adapterMainRv.getData());
//                Collections.sort(ascendingTracks, (t1, t2) -> t1.getPosition() - t2.getPosition());
//
//                adapterMainRv.setDataTracksList(ascendingTracks);
                Toast.makeText(getApplicationContext(), "Sorted as retrieved from API!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.sort_ascending: {
//                ArrayList<ChartDataTracks> ascendingTracks = new ArrayList<>(adapterMainRv.getData());
//                Collections.sort(ascendingTracks, (t1, t2) -> t1.getDuration() - t2.getDuration());
//
//                adapterMainRv.setDataTracksList(ascendingTracks);
                Toast.makeText(getApplicationContext(), "Sorted ascending by track duration!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.sort_descending: {
//                ArrayList<ChartDataTracks> descendingTracks = new ArrayList<>(adapterMainRv.getData());
//                Collections.sort(descendingTracks, (t1, t2) -> t2.getDuration() - t1.getDuration());
//
//                adapterMainRv.setDataTracksList(descendingTracks);
                Toast.makeText(getApplicationContext(), "Sorted descending by track duration!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.delete_all: {
                tracksViewModel.deleteAll();
                rvView.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
