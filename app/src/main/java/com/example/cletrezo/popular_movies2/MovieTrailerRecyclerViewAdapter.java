package com.example.cletrezo.popular_movies2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieTrailerRecyclerViewAdapter extends RecyclerView.Adapter<MovieTrailerRecyclerViewAdapter.CustomViewHolder> {
    private ArrayList<String> videoArrayList;
    private Context context;
    private LayoutInflater inflater;


    public MovieTrailerRecyclerViewAdapter(Context context, ArrayList<String> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
        inflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //created once and pass to viewHolder to be held for future used and avoid the expensive inflater operation
        View view = inflater.inflate(R.layout.single_row_trailer_item,parent , false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // pass the view held by customViewHolder class.
        int count= position + 1;
        holder.trailerTextView.setText(videoArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        // the number of items passed to be recycled

        return videoArrayList.size();

    }



    class CustomViewHolder extends RecyclerView.ViewHolder{
        // single rows views held
        ImageView trailerImage;
        TextView trailerTextView;


        public CustomViewHolder(View itemView) {
            super(itemView);
            this.trailerImage = itemView.findViewById(R.id.trailer_image);
            this.trailerTextView = itemView.findViewById(R.id.trailer_textview);
        }
    }
}
