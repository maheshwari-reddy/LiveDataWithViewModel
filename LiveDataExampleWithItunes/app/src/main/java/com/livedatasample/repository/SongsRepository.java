package com.livedatasample.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.livedatasample.domain.GeneralResponse;
import com.livedatasample.domain.Songs;
import com.livedatasample.networking.RestApiService;
import com.livedatasample.networking.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsRepository {

    private ArrayList<Songs> songsArrayList = new ArrayList<>();
    private MutableLiveData<List<Songs>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public SongsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Songs>> fetchSongs() {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<GeneralResponse> call = apiService.fetchSongs();
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                GeneralResponse generalResponse = response.body();
                if (generalResponse != null && generalResponse.getResults() != null) {
                    songsArrayList = (ArrayList<Songs>) generalResponse.getResults();
                    mutableLiveData.setValue(songsArrayList);
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return mutableLiveData;
    }
}
