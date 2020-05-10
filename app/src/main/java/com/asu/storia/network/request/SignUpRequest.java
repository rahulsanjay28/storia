package com.asu.storia.network.request;

import android.util.Log;

import com.asu.storia.network.ServiceGenerator;
import com.asu.storia.network.StoriaAPI;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRequest {

    private static final String TAG = SignUpRequest.class.getSimpleName();
    private com.asu.storia.network.response.Response callback;

    public void signup(String userId, String password, String name,
                       final com.asu.storia.network.response.Response callback) {
        this.callback = callback;

        StoriaAPI storiaAPI = ServiceGenerator.createService(StoriaAPI.class);

        RequestBody userIdInRequestBody = RequestBody.create(okhttp3.MultipartBody.FORM,
                userId);
        RequestBody passwordInRequestBody = RequestBody.create(okhttp3.MultipartBody.FORM,
                password);
        RequestBody nameInRequestBody = RequestBody.create(okhttp3.MultipartBody.FORM,
                name);

        Call<ResponseBody> call = storiaAPI.signup(userIdInRequestBody, passwordInRequestBody,
                nameInRequestBody);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "message " + response.message());
                    if(response.body() != null) {
                        Log.i(TAG, "Body " + response.body().string());
                    }
                    callback.onResponse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }
}
