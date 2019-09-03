package com.livedatasample.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.livedatasample.domain.Songs;
import com.livedatasample.repository.SongsRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private SongsRepository songsRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        songsRepository = new SongsRepository(application);
    }

    public LiveData<List<Songs>> getAllSongs() {
        return songsRepository.fetchSongs();
    }
}
