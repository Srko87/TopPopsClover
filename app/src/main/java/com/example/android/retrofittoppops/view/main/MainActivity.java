package com.example.android.retrofittoppops.view.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofittoppops.R;
import com.example.android.retrofittoppops.adapter.MainChartAdapter;
import com.example.android.retrofittoppops.commons.thread.DefaultExecutorSupplier;
import com.example.android.retrofittoppops.database.entity.ArtistEntity;
import com.example.android.retrofittoppops.database.entity.TrackEntity;
import com.example.android.retrofittoppops.model.TrackArtistHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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

    private MainChartAdapter adapterMainRv;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(null);


        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        adapterMainRv = new MainChartAdapter();
        rvView.setAdapter(adapterMainRv);

        mainViewModel.fetchCharts();

        mainViewModel.onError().observe(this, errorMessage -> {
            // TODO
            // Why not use errorMessage?
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage(R.string.error_message)
                    .setTitle(getString(R.string.error_dialog_title))
                    .setPositiveButton(R.string.button_ok, (dialog1, id) -> {
                        dialog1.dismiss();
                    })
                    .create();
            dialog.show();
        });

        mainViewModel.getChartLiveData().observe(this, chartEntity -> {
            if (chartEntity != null) {
                DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

                    List<TrackEntity> chartTracks = mainViewModel.getTracksById(chartEntity.getTracks());
                    List<String> artistIds = new ArrayList<>();
                    for (TrackEntity item : chartTracks) {
                        artistIds.add(item.getArtistId());
                    }

                    List<ArtistEntity> chartArtists = mainViewModel.getArtistsById(artistIds);
                    List<TrackArtistHelper> finalAdapterList = new ArrayList<>();
                    for (TrackEntity track : chartTracks) {
                        TrackArtistHelper item = new TrackArtistHelper();
                        item.track = track;
                        for (ArtistEntity artist : chartArtists) {
                            if (track.getArtistId().equals(artist.getId())) {
                                item.artist = artist;
                                break;
                            }
                        }
                        finalAdapterList.add(item);
                    }

                    DefaultExecutorSupplier.getInstance().forMainThreadTasks().execute(() -> {
                        if (!finalAdapterList.isEmpty()) {
                            tvNoData.setVisibility(View.GONE);
                            adapterMainRv.updateItems(finalAdapterList);
                            rvView.setVisibility(View.VISIBLE);
                        } else {
                            rvView.setVisibility(View.GONE);
                            tvNoData.setVisibility(View.VISIBLE);
                        }
                    });
                });
            }
        });

        pullToRefresh.setOnRefreshListener(() -> {
            mainViewModel.fetchCharts();
            pullToRefresh.setRefreshing(false);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_normal: {
                adapterMainRv.sortByPosition();
                Toast.makeText(getApplicationContext(), "Sorted as retrieved from API!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.sort_ascending: {
                adapterMainRv.sortByDurationAsc();
                Toast.makeText(getApplicationContext(), "Sorted ascending by track duration!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.sort_descending: {
                adapterMainRv.sortByDurationDesc();
                Toast.makeText(getApplicationContext(), "Sorted descending by track duration!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
