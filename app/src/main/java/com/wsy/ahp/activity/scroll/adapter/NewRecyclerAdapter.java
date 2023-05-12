package com.wsy.ahp.activity.scroll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.wsy.ahp.R;

import java.util.List;

public class NewRecyclerAdapter extends RecyclerView.Adapter<NewRecyclerHolder> {

    List<String> mData;
    LayoutInflater mLayoutInflater;

    public NewRecyclerAdapter(Context context,List<String> data) {
        this.mData = data;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewRecyclerHolder(mLayoutInflater.inflate(R.layout.item_view2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewRecyclerHolder holder, int position) {
        holder.newBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class NewRecyclerHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public NewRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        textView  = itemView.findViewById(R.id.textview2);
    }

    public void newBind(String title) {
        textView.setText(title);
    }
}

