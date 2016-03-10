package com.group4.paladar.Fragments.views;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.group4.paladar.FireBaseLogic.DiningEventHandler;
import com.group4.paladar.FireBaseStructures.event.DiningEvent;
import com.group4.paladar.FireBaseStructures.user.UserFirebase;
import com.group4.paladar.MainActivity;
import com.group4.paladar.R;
import com.group4.paladar.Utils.ImageHandler;

import java.util.ArrayList;

/**
 * Created by Surface on 2016-02-25.
 */
public class SearchFragment  extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter myAdapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search,container,false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        LinearLayoutManager    mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        final ArrayList<DiningEvent> diningevents = new ArrayList<DiningEvent>();

        Firebase ref = new Firebase("https://paladar-android.firebaseio.com/events");
        Query queryRef = ref.orderByKey();
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    DiningEvent event = child.getValue(DiningEvent.class);
                    diningevents.add(event);
                }

                myAdapter = new MyAdapter(diningevents);

                // specify an adapter (see also next example)
                mRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return root;
    }



}
