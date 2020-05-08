package com.asu.storia.network;

import android.util.Log;

import com.asu.storia.utils.StoriaUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetLatestStoriesRequest implements Callback<List<String>> {

    private static final String TAG = GetLatestStoriesRequest.class.getSimpleName();
    private GetLatestStoriesResponse callback;

    public void getLatestStories(GetLatestStoriesResponse callback){
        this.callback = callback;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StoriaUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        StoriaAPI storiaAPI = retrofit.create(StoriaAPI.class);

        Call<List<String>> call = storiaAPI.getLatestStories();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
        if(response.isSuccessful()) {
            Log.i(TAG, "onResponse");
            callback.onResponse(response.body());
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<String>> call, Throwable t) {
        t.printStackTrace();
    }
}
