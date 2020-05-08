package com.asu.storia.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoriaAPI {

    @GET("getLatestStories")
    Call<List<String>> getLatestStories();
}
