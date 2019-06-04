package com.rgs.bakingapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rgs.bakingapp1.masterdetailflow.DisplaydataListActivity;
import com.rgs.bakingapp1.steps.stepsListActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main_adap extends RecyclerView.Adapter<Main_adap.ViewHolder> {

    private ArrayList<POJO> list;
    Context context;

    public Main_adap(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<POJO> list) {
        this.list = list;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pos(position);
        Log.d("position1" , String.valueOf(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.main_item) TextView items;
//        @BindView(R.id.recycler_items) RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            items = itemView.findViewById(R.id.main_item);
            itemView.setOnClickListener(this);
        }



        void pos(int pos)
        {
            POJO pojo = list.get(pos);
            String name = pojo.getName();
            Log.d("position" , name);
            items.setText(name);
        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            Log.d("pos" , String.valueOf(pos));
            Intent intent = new Intent(context , stepsListActivity.class);
            intent.putExtra("position" , pos);
            intent.putParcelableArrayListExtra("stepsList", (ArrayList<? extends Parcelable>) list.get(pos).getSteps());
            intent.putParcelableArrayListExtra("ingredientsList", (ArrayList<? extends Parcelable>) list.get(pos).getIngredients());
            context.startActivity(intent);

        }
    }
}
