package com.projet.nsv.nsvapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import com.projet.nsv.nsvapp.R;
import com.projet.nsv.nsvapp.adapter.ReceipeListAdapter;
import com.projet.nsv.nsvapp.manager.WsManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReceipeListActivity extends AppCompatActivity {

    private static final String TAG = "ReceipeListActivity";

    // variables
    private ArrayList<String> mReceipeTitles = new ArrayList<>();
    private ArrayList<String> mImagesUrls = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ImageView ivImageFromUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        Log.d(TAG, "onCreate: started.");

        final WsManager wsManager = new WsManager();

        initImageBitmaps();

    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");


        mImagesUrls.add("https://i.redd.it/jwdbh6wvh1f31.jpg");
        mReceipeTitles.add("Hot-chicken sandwiches");

        mImagesUrls.add("https://i.redd.it/8araqat0z0f31.jpg");
        mReceipeTitles.add("Bacon, Sausage, Tomato Pizza");

        mImagesUrls.add("https://i.redd.it/otxvjxu4f0f31.png");
        mReceipeTitles.add("Peanut Butter Swirl \"Brookies\"");

        mImagesUrls.add("https://i.redd.it/gfgufd05xwe31.jpg");
        mReceipeTitles.add("Egg yolk ravioli");

        mImagesUrls.add("https://i.redd.it/84gnoey7awe31.png");
        mReceipeTitles.add("Blueberry Lemon Scones with fresh whipped cream");

        mImagesUrls.add("https://i.redd.it/05egvu6md1f31.jpg");
        mReceipeTitles.add("Red wine braised lamb shanks with creamy mash");

        mImagesUrls.add("https://i.redd.it/dieir5rxa1f31.jpg");
        mReceipeTitles.add("Cream Horns");

        mImagesUrls.add("https://i.redd.it/68li81r7zxe31.jpg");
        mReceipeTitles.add("A Footlong Maine Lobster Roll");

        mImagesUrls.add("https://i.redd.it/ownq6lhtj0f31.jpg");
        mReceipeTitles.add("Crab noodle soup");

        mImagesUrls.add("https://i.redd.it/off2p9zh30f31.jpg");
        mReceipeTitles.add("Mushroom & Bacon Farfalle Alfredo with Green Chillies and Truffle Oil");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        ReceipeListAdapter adapter = new ReceipeListAdapter( mReceipeTitles, mImagesUrls, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void openReceipeDetailsActivity() {
        Intent intent = new Intent(this, ReceipeDetailsActivity.class);
        startActivity(intent);
    }
}



