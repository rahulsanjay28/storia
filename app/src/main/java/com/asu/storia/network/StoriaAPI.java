package com.asu.storia.network;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface StoriaAPI {

    @Multipart
    @POST("signin")
    Call<ResponseBody> signin(
            @Part("user_id") RequestBody userId,
            @Part("password") RequestBody password);

    @Multipart
    @POST("signup")
    Call<ResponseBody> signup(
            @Part("user_id") RequestBody userId,
            @Part("password") RequestBody password,
            @Part("name") RequestBody name);

    @Multipart
    @POST("getLatestStories")
    Call<ResponseBody> getLatestStories(@Part("user_id") RequestBody userId);

    @Multipart
    @POST("uploadStory")
    Call<ResponseBody> uploadStory(
            @Part("user_id") RequestBody userId,
            @Part MultipartBody.Part storiaImage
    );
}
