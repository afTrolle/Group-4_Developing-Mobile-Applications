package com.group4.paladar.Fragments.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.paladar.FireBaseStructures.event.DiningEvent;
import com.group4.paladar.R;
import com.group4.paladar.Utils.ImageHandler;

import java.util.ArrayList;

/**
 * Created by Surface on 2016-03-10.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private ArrayList<DiningEvent> eventArrayList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View cardView;
        public ViewHolder(View v) {
            super(v);
            cardView = v;
        }
    }

    public MyAdapter(ArrayList<DiningEvent> eventArrayList) {
        this.eventArrayList = eventArrayList;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //ViewHolder vh = ;
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView title = (TextView) holder.cardView.findViewById(R.id.card_title);
        TextView summary = (TextView) holder.cardView.findViewById(R.id.card_date);
        ImageView eventImage = (ImageView) holder.cardView.findViewById(R.id.card_eventImage);

        title.setText(eventArrayList.get(position).getTitle());
        summary.setText(eventArrayList.get(position).getSummary());
        eventImage.setImageBitmap(ImageHandler.getImageasBitmap(eventArrayList.get(position).getFoodImage()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

}
