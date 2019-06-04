package com.rgs.bakingapp1.steps;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.rgs.bakingapp1.R;
import com.rgs.bakingapp1.dummy.DummyContent;

public class stepsDetailFragment extends Fragment {

    private SimpleExoPlayer player;
    private SimpleExoPlayerView playerView;

    PlayerView playerView;

    String somename;
    String someAnother;
    TextView discription;
    TextView vidurl;

    public stepsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey("v1")) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            somename= getArguments().getString("v1");
            someAnother = getArguments().getString("v2");
            Activity activity = this.getActivity();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.steps_detail, container, false);
        playerView = rootView.findViewById(R.id.video_view);
        discription = rootView.findViewById(R.id.step_ins);
        discription.setText(somename);

        Toast.makeText(getActivity(), getArguments().getString("v1") , Toast.LENGTH_SHORT).show();
        return rootView;
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
    }
}
