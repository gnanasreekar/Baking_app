package com.rgs.bakingapp1.steps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rgs.bakingapp1.POJO;
import com.rgs.bakingapp1.R;
import com.rgs.bakingapp1.dummy.DummyContent;
import com.rgs.bakingapp1.masterdetailflow.DisplaydataDetailFragment;

import java.util.ArrayList;
import java.util.List;


public class stepsListActivity extends AppCompatActivity {


    private boolean mTwoPane;
    private POJO pojos;
    private ArrayList<POJO.StepsBean> stepsBeans;
    ArrayList<POJO.IngredientsBean> ingredientsBeans;
    Intent intent;
    private static stepsDetailFragment stepsDetailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        stepsBeans = getIntent().getParcelableArrayListExtra("stepsList");
        ingredientsBeans = getIntent().getParcelableArrayListExtra("ingredientsList");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.steps_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.steps_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, stepsBeans, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final stepsListActivity mParentActivity;
        ArrayList<POJO.StepsBean> stepsBeans;
        private final boolean mTwoPane;


        SimpleItemRecyclerViewAdapter(stepsListActivity parent, ArrayList<POJO.StepsBean> items, boolean twoPane) {
            stepsBeans = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
                    POJO.StepsBean stepsBean = stepsBeans.get(position);
                    String step = stepsBean.getShortDescription();
                    holder.pos(step);



        }

        @Override
        public int getItemCount() {
            return stepsBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);

                mContentView = (TextView) view.findViewById(R.id.content);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        int pos=getAdapterPosition();
                       stepsDetailFragment = new stepsDetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("v1" , stepsBeans.get(pos).getDescription());
                        bundle.putString("v2" , stepsBeans.get(pos).getVideoURL() );
                        if (mTwoPane) {
                            stepsDetailFragment fragment = new stepsDetailFragment();
                            fragment.setArguments(bundle);
                            mParentActivity.getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.steps_detail_container, fragment)
                                    .commit();
                        } else {
                            Context context = view.getContext();
                            Intent intent = new Intent(context, stepsDetailActivity.class);
                            intent.putExtras(bundle);

                            context.startActivity(intent);
                        }
                    }
                });
            }
            void pos(String pos)
            {
                mContentView.setText(pos);
            }
        }



    }
}

