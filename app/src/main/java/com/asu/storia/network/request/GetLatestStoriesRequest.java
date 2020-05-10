package com.asu.storia.network.request;

import com.asu.storia.network.ServiceGenerator;
import com.asu.storia.network.StoriaAPI;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetLatestStoriesRequest {

    private static final String TAG = GetLatestStoriesRequest.class.getSimpleName();

    public void getLatestStories(String userId, final com.asu.storia.network.response.Response callback) {

        StoriaAPI storiaAPI = ServiceGenerator.createService(StoriaAPI.class);

        RequestBody userIdInBody = RequestBody.create(okhttp3.MultipartBody.FORM, userId);

        Call<ResponseBody> call = storiaAPI.getLatestStories(userIdInBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        callback.onResponse(response.body().string());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onResponse(t.getMessage());
            }
        });
    }
}
