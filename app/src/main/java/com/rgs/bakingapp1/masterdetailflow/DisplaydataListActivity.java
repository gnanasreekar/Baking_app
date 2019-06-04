package com.rgs.bakingapp1.masterdetailflow;

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

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;


public class DisplaydataListActivity extends AppCompatActivity {

    static DisplaydataDetailFragment fragment ;
    private boolean mTwoPane;
    private POJO pojos;
    private ArrayList<POJO.StepsBean> stepsBeans;
    ArrayList<POJO.IngredientsBean> ingredientsBeans;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydata_list);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        stepsBeans = getIntent().getParcelableArrayListExtra("stepsList");
        ingredientsBeans = getIntent().getParcelableArrayListExtra("ingredientsList");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.displaydata_detail_container) != null) {

            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.displaydata_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        Toast.makeText(this, "" + stepsBeans.size(), Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, stepsBeans, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        ArrayList<POJO.StepsBean> stepsBeans;
        private final DisplaydataListActivity mParentActivity;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(DisplaydataDetailFragment.ARG_ITEM_ID, item.id);
                    Bundle bundle = new Bundle();
                    bundle.putString("v1" , "hello");
                    fragment.setArguments(bundle);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();

                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DisplaydataDetailActivity.class);
                    intent.putExtra(DisplaydataDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };



        SimpleItemRecyclerViewAdapter(DisplaydataListActivity parent, ArrayList<POJO.StepsBean> items, boolean twoPane) {
            stepsBeans = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displaydata_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.pos(position);
            holder.itemView.setOnClickListener(mOnClickListener);
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
                        fragment = new DisplaydataDetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("v1" , stepsBeans.get(pos).getDescription());
                        bundle.putString("v2" , stepsBeans.get(pos).getVideoURL() );

                    }
                });
            }

            void pos(int pos) {
                POJO.StepsBean stepsBean = stepsBeans.get(pos);
                String step = stepsBean.getShortDescription();
                mContentView.setText(step);
            }
        }
    }
}
