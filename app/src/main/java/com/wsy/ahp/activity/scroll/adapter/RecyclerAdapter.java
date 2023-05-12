package com.wsy.ahp.activity.scroll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;


import com.wsy.ahp.R;
import com.wsy.ahp.activity.scroll.view.GridModel;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {

    List<String> mData;
    List<Integer> mImageData;
    LayoutInflater mLayoutInflater;

    public RecyclerAdapter(Context context,List<String> data,List<Integer> imageData) {
        this.mData = data;
        this.mImageData = imageData;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(mLayoutInflater.inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.bind(mData.get(position),mImageData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class RecyclerHolder extends RecyclerView.ViewHolder {

    TextView textView;
    AppCompatImageView imageView;


    public RecyclerHolder(@NonNull View itemView) {
        super(itemView);
        textView  = itemView.findViewById(R.id.textview);
        imageView  = itemView.findViewById(R.id.image);
    }

    public void bind(String title,Integer image) {

        textView.setText(title);
        imageView.setImageResource(image);
    }
}

