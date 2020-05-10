package com.asu.storia.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;

public class StoriaSharedPreferenceUtils {

    private static StoriaSharedPreferenceUtils mInstance;

    public static StoriaSharedPreferenceUtils getInstance(){
        if(mInstance == null){
            mInstance = new StoriaSharedPreferenceUtils();
        }
        return mInstance;
    }

    public void setUserId(Context context, String userId){
        SharedPreferences sharedPref = context.getSharedPreferences(StoriaUtils.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(StoriaUtils.SHARED_PREF_USER_ID, userId);
        editor.apply();
    }

    public String getUserId(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(StoriaUtils.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE);

        return sharedPref.getString(StoriaUtils.SHARED_PREF_USER_ID, "");
    }

    public void setStories(Context context, String stories){
        SharedPreferences sharedPref = context.getSharedPreferences(StoriaUtils.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(StoriaUtils.SHARED_PREF_STORY_URLS, stories);
        editor.apply();
    }

    public String getStories(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(StoriaUtils.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE);

        return sharedPref.getString(StoriaUtils.SHARED_PREF_STORY_URLS, "");
    }
}
