package com.wsy.ahp.activity.scroll.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wsy.ahp.R;
import com.wsy.ahp.activity.scroll.adapter.NewRecyclerAdapter;


import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        final NewRecyclerAdapter adapter = new NewRecyclerAdapter(getContext(),getData());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        for(int i = 0; i < 100; i ++) {
            data.add("ChildView item " + i);
        }
        return data;
    }
}
