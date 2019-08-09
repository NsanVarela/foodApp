package com.projet.nsv.nsvapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.projet.nsv.nsvapp.R;
import com.projet.nsv.nsvapp.model.Receipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReceipeListAdapter extends RecyclerView.Adapter<ReceipeListAdapter.ViewHolder> {

    private static final String TAG = "ReceipeListAdapter";

    private List<Receipe> receipes;
    private Context context;

    public ReceipeListAdapter(List<Receipe> receipes, Context context) {
        this.receipes = receipes;
        this.context = context;
    }

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

        Receipe receipe = receipes.get(position);
        holder.receipeId.setTag(receipe.getId());
        holder.receipeTitle.setText(receipe.getTitle());
        Picasso.get().load(receipe.getPhoto()).into(holder.receipeImage);
        holder.receipeNote.setRating(receipe.getNote());
    }

    // Retourne le nombre d'items dans la liste
    @Override
    public int getItemCount() {
        return receipes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView receipeId;
        TextView receipeTitle;
        ImageView receipeImage;
        RatingBar receipeNote;


        ViewHolder(View v) {
            super(v);
            receipeId = v.findViewById(R.id.card_view);
            receipeTitle = v.findViewById(R.id.tv_receipeTitle);
            receipeImage = v.findViewById(R.id.iv_receipeImage);
            receipeNote = v.findViewById(R.id.rating);
        }


    }


}