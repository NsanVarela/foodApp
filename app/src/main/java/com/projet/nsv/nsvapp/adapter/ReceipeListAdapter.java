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
    private ReceipeListAdapter.ItemClickListener itemClickListener;
    private Context context;

    public ReceipeListAdapter(List<Receipe> receipes, Context context, ReceipeListAdapter.ItemClickListener listener) {
        this.receipes = receipes;
        this.context = context;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipe_item, parent, false);
        ViewHolder holder = new ViewHolder(view, itemClickListener);
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

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView receipeId;
        TextView receipeTitle;
        ImageView receipeImage;
        RatingBar receipeNote;
        private ReceipeListAdapter.ItemClickListener itemClickListener;


        ViewHolder(View v, ItemClickListener itemClickListener) {
            super(v);
            this.itemClickListener = itemClickListener;
            receipeId = v.findViewById(R.id.card_view);
            receipeTitle = v.findViewById(R.id.tv_receipeTitle);
            receipeImage = v.findViewById(R.id.iv_receipeImage);
            receipeNote = v.findViewById(R.id.rating);
            v.setOnClickListener(this);
        }

        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("appFood", "onClick " + position);
            if(itemClickListener != null) {
                itemClickListener.onClickListener(position);
            }
        }
    }


    public interface ItemClickListener {
        void onClickListener(int position);
    }


}