package com.asu.storia.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asu.storia.R;
import com.asu.storia.model.UserStories;
import com.asu.storia.network.request.GetLatestStoriesRequest;
import com.asu.storia.network.response.Response;
import com.asu.storia.utils.StoriaSharedPreferenceUtils;
import com.asu.storia.viewmodel.UserStoriesViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class StoryFragment extends Fragment {

    private static final String TAG = StoryFragment.class.getSimpleName();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private StoryRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StoryFragment newInstance(int columnCount) {
        StoryFragment fragment = new StoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            final Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);

            adapter = new StoryRecyclerViewAdapter(mListener);

            String userId = StoriaSharedPreferenceUtils.getInstance().getUserId(context);
            new GetLatestStoriesRequest().getLatestStories(userId, new Response() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    Log.i(TAG, "onResponse " + response);
                    List<UserStories> stories = gson.fromJson(response,
                            new TypeToken<List<UserStories>>() {
                            }.getType());
                    List<UserStoriesViewModel> userStoriesViewModels = new ArrayList<>();
                    List<String> storyUrls = new ArrayList<>();
                    for (UserStories story : stories) {
                        for (String url : story.getStories()) {
                            storyUrls.add(url);
                            userStoriesViewModels.add(new UserStoriesViewModel(story.getName(), url));
                        }
                    }
                    StoriaSharedPreferenceUtils.getInstance().setStories(context, new Gson().toJson(storyUrls));
                    adapter.setValues(userStoriesViewModels);
                    adapter.setHasStableIds(true);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(String message) {
                    Log.i(TAG, "onFailure");
                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(UserStoriesViewModel item);
    }
}
