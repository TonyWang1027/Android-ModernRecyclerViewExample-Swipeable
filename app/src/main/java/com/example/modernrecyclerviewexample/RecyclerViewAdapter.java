package com.example.modernrecyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    // This variable holds list of ExampleItem that passed from MainActivity class
    private ArrayList<ExampleItem> mExampleItemList;

    /* RecyclerViewAdapter Class Constructor */
    public RecyclerViewAdapter(ArrayList<ExampleItem> exampleItemList) {
        mExampleItemList = exampleItemList;
    }

    /* Override methods */
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Instantiates a layout XML file into its corresponding View Java Object
        // parent.getContext()  ---  returns the context the view is running in
        // inflate  ---  bind with XML layout file
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);

        // Create an RecyclerViewHolder that we just wrote down below and pass the view
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    /*
     * Get each ExampleItem from mExampleItemList that we passed from MainActivity class
     * onBindViewHolder will look at each item inside mExampleItemList,
     * and set the view to the RecyclerView by using ExampleViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Get each ExampleItem
        ExampleItem currentItem = mExampleItemList.get(position);

        // Set each ExampleItem into RecyclerList by setting the ExampleViewHolder
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    /* Define how many items will be in our ExampleItemList */
    @Override
    public int getItemCount() {
        return mExampleItemList.size();
    }

    /* Create ViewHolder inner class - this inner class is needed when creating the RecyclerView adapter */
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        /* Create ViewHolder for each view inside our example_item.xml file */
        public TextView mTextView1;
        public TextView mTextView2;

        /* Constructor */
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            /* Create reference to our views */
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

}
