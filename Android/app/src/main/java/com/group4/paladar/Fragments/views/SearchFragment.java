package com.group4.paladar.Fragments.views;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group4.paladar.R;

/**
 * Created by Surface on 2016-02-25.
 */
public class SearchFragment  extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search,container,false);


        return root;


    }
}
