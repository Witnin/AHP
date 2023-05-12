package com.wsy.ahp.activity.scroll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.wsy.ahp.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchRecyclerHolder> {

    LayoutInflater mLayoutInflater;

    public SearchAdapter(Context context) {
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SearchRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchRecyclerHolder(mLayoutInflater.inflate(R.layout.search_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

class SearchRecyclerHolder extends RecyclerView.ViewHolder {

    TextView textView;
    AppCompatImageView imageView;


    public SearchRecyclerHolder(@NonNull View itemView) {
        super(itemView);

    }

    public void bind(Integer image) {

    }
}

