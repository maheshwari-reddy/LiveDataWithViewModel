package com.livedatasample.networking;

import com.livedatasample.domain.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {

    @GET("/search?term=Michael+jackson")
    Call<GeneralResponse> fetchSongs();
}
