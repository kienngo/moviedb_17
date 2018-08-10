package com.example.admin.moviebd.screen.television;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.screen.BaseFragment;

public class TelevisionFragment extends BaseFragment {

    public static TelevisionFragment newInstance() {
        TelevisionFragment televisionFragment = new TelevisionFragment();
        return televisionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_television, container, false);
        return view;
    }
}
