package com.asu.storia.network.response;

public interface Response {
    public void onResponse(String response);
    public void onFailure(String message);
}
