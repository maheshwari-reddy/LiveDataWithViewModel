package com.livedatasample.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.livedatasample.R;
import com.livedatasample.domain.Songs;
import com.livedatasample.ui.adapter.SongsAdapter;
import com.livedatasample.view_model.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SongsAdapter.ISongsAdapterCallBack {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private MainViewModel mainViewModel;
    private SongsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getPopularSongs();
        swipeRefresh.setOnRefreshListener(this::getPopularSongs);
    }

    private void getPopularSongs() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllSongs().observe(this, new Observer<List<Songs>>() {
            @Override
            public void onChanged(@Nullable List<Songs> songsList) {
                swipeRefresh.setRefreshing(false);
                prepareRecyclerView(songsList);
            }
        });
    }

    private void prepareRecyclerView(List<Songs> songsList) {
        adapter = new SongsAdapter(this,songsList,this);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectedSong(int id) {

    }
}
