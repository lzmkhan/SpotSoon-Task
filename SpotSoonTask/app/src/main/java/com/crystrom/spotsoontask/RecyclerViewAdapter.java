package com.crystrom.spotsoontask;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marcus Khan on 5/10/2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<Video> videoList;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextTitle, mTextPostedTime, mTextDesc;
        CardView mCardView;

        public ViewHolder(CardView cardView){
            super(cardView);
            mCardView = cardView;
            mImageView = (ImageView)cardView.findViewById(R.id.videoImage);
            mTextTitle = (TextView) cardView.findViewById(R.id.videoTitle);
            mTextPostedTime = (TextView) cardView.findViewById(R.id.videoPost);
            mTextDesc = (TextView) cardView.findViewById(R.id.videoDesc);

        }

    }

    public RecyclerViewAdapter(ArrayList<Video> v){
        videoList = v;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);


        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextTitle.setText(videoList.get(position).getVideoTitle());
        holder.mImageView.setImageResource(videoList.get(position).getVideoImageIDs());
        holder.mTextDesc.setText(videoList.get(position).getVideoDescription());
        holder.mTextPostedTime.setText(videoList.get(position).getVideoPostTime());
        Log.i("RecyclerViewadapter","OnBindViewHolder");
        Log.i("Recyclerviewadapter",""+getItemCount());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return videoList.size();
    }


}
