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

public class SignInRequest {

    private static final String TAG = SignInRequest.class.getSimpleName();
    private com.asu.storia.network.response.Response callback;

    public void signin(String userId, String password, final com.asu.storia.network.response.Response callback) {
        this.callback = callback;

        StoriaAPI storiaAPI = ServiceGenerator.createService(StoriaAPI.class);

        RequestBody userIdInRequestBody = RequestBody.create(okhttp3.MultipartBody.FORM,
                userId);
        RequestBody passwordInRequestBody = RequestBody.create(okhttp3.MultipartBody.FORM,
                password);

        Call<ResponseBody> call = storiaAPI.signin(userIdInRequestBody, passwordInRequestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.isSuccessful()) {
                        Log.i(TAG, "message " + response.message());
                        if (response.body() != null) {
                            Log.i(TAG, "Body " + response.body().string());
                        }
                        callback.onResponse(response.body().string());
                    }else{
                        Log.i(TAG, "Error " + response.errorBody() + " " + response.code());
                        callback.onFailure(response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, t.getMessage());
                callback.onFailure(t.getMessage());
            }
        });
    }
}
