package com.projet.nsv.nsvapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet.nsv.nsvapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReceipeListAdapter extends RecyclerView.Adapter<ReceipeListAdapter.ViewHolder> {

    private static final String TAG = "ReceipeListAdapter";
    private ArrayList<String> mReceipeTitles = new ArrayList<>();
    private ArrayList<String> mReceipeImages = new ArrayList<>();
    private Context mContext;

    public ReceipeListAdapter(ArrayList<String> mReceipeTitles, ArrayList<String> mReceipeImages, Context mContext) {
        this.mReceipeTitles = mReceipeTitles;
        this.mReceipeImages = mReceipeImages;
        this.mContext = mContext;
    }

    /* Inflate la vue */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipe_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Picasso.get().load(mReceipeImages.get(position)).into(holder.receipeImage);

        holder.receipeTitle.setText(mReceipeTitles.get(position));

        // Pop up pour un message au clic sur l'image
        holder.receipe_parent_llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mReceipeTitles.get(position));

                Toast.makeText(mContext, mReceipeTitles.get(position), Toast.LENGTH_SHORT).show();



            }
        });

    }

    // Retourne le nombre d'items dans la liste
    @Override
    public int getItemCount() {
        return mReceipeTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView receipeImage;
        TextView receipeTitle;
        LinearLayout receipe_parent_llayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receipeImage = itemView.findViewById(R.id.iv_receipeImage);
            receipeTitle = itemView.findViewById(R.id.tv_receipeTitle);
            receipe_parent_llayout = itemView.findViewById(R.id.receipe_parent_llayout);
        }
    }



}