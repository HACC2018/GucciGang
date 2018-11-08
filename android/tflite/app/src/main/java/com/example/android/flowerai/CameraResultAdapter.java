package com.example.android.flowerai;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CameraResultAdapter extends RecyclerView.Adapter<CameraResultAdapter.MyViewHolder> {
    private String[] mPlantNames;
    private String[] mPlantPcts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mPlantName;
        public TextView mPlantPct;
        public MyViewHolder(View v) {
            super(v);
            mPlantName = v.findViewById(R.id.recyclerPlantName);
            mPlantPct = v.findViewById(R.id.recyclerPlantPct);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CameraResultAdapter(String[] plantNames, String[] plantPcts) {
        mPlantNames = plantNames;
        mPlantPcts = plantPcts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CameraResultAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_camera_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mPlantName.setText(mPlantNames[position]);
        holder.mPlantPct.setText(mPlantPcts[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPlantNames.length;
    }
}