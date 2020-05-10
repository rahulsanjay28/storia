package com.asu.storia.network.request;

import android.util.Log;

import com.asu.storia.network.ServiceGenerator;
import com.asu.storia.network.StoriaAPI;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadStoryRequest {

    private static final String TAG = UploadStoryRequest.class.getSimpleName();

    public void uploadStory(String userId, File file, MediaType type, final com.asu.storia.network.response.Response callback) {

        StoriaAPI storiaAPI = ServiceGenerator.createService(StoriaAPI.class);

        RequestBody requestFile = RequestBody.create(type, file);

        MultipartBody.Part storiaImageInRequestBody =
                MultipartBody.Part.createFormData("storia_image", file.getName(), requestFile);

        RequestBody userIdInRequestBody = RequestBody.create(okhttp3.MultipartBody.FORM,
                userId);

        Call<ResponseBody> call = storiaAPI.uploadStory(userIdInRequestBody, storiaImageInRequestBody);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse");
                callback.onResponse("uploaded successfully");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getMessage());
                callback.onFailure("upload failed");
            }
        });
    }
}
