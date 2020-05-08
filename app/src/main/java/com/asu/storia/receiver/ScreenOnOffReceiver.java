package com.asu.storia.receiver;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.asu.storia.network.GetLatestStoriesRequest;
import com.asu.storia.network.GetLatestStoriesResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.List;

public class ScreenOnOffReceiver extends BroadcastReceiver {

    private static final String TAG = ScreenOnOffReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.i(TAG, "ACTION SCREEN OFF");
            final PendingResult pendingResult = goAsync();
            Task asyncTask = new Task(pendingResult, intent, context);
            asyncTask.execute();
        }
    }

    private static class Task extends AsyncTask<String, Void, Void> {

        private final PendingResult pendingResult;
        private final Intent intent;
        private final Context context;

        private Task(PendingResult pendingResult, Intent intent, Context context) {
            this.pendingResult = pendingResult;
            this.intent = intent;
            this.context = context;
        }

        @Override
        protected Void doInBackground(String... voids) {
            new GetLatestStoriesRequest().getLatestStories(new GetLatestStoriesResponse(){
                @Override
                public void onResponse(List<String> response) {
                    final WallpaperManager wallpaperManager = WallpaperManager.getInstance(context.getApplicationContext());
                    Target target = new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            Log.i(TAG, "onBitmapLoaded");
                            try {
                                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                            Log.i(TAG, "onBitmapFailed");
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                            Log.i(TAG, "onPrepareLoad");
                        }
                    };
                    Picasso.get().load(response.get(0)).into(target);
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pendingResult.finish();
        }
    }
}
