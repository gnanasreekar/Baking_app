package com.rgs.bakingapp1.masterdetailflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rgs.bakingapp1.R;
import com.rgs.bakingapp1.dummy.DummyContent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A fragment representing a single Display_data detail screen.
 * This fragment is either contained in a {@link DisplaydataListActivity}
 * in two-pane mode (on tablets) or a {@link DisplaydataDetailActivity}
 * on handsets.
 */
public class DisplaydataDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "v1";

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    TextView textView;
    Unbinder unbinder;
    String disc, vidurl;

    public DisplaydataDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.displaydata_detail, container, false);
        textView = rootView.findViewById(R.id.step_ins);

        // Show the dummy content as text in a TextView.
        disc = getArguments().getString("v1");
        vidurl = getArguments().getString("v2");
        Toast.makeText(getActivity(), disc, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), vidurl, Toast.LENGTH_SHORT).show();
        Log.d("disc1", disc);
        textView.setText(disc);

        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

}
