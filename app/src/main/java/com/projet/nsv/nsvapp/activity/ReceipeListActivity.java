package com.projet.nsv.nsvapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.projet.nsv.nsvapp.R;
import com.projet.nsv.nsvapp.adapter.ReceipeListAdapter;
import com.projet.nsv.nsvapp.manager.WsManager;
import com.projet.nsv.nsvapp.model.Receipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceipeListActivity extends AppCompatActivity implements WsManager.Listener, ReceipeListAdapter.ItemClickListener {

    private static final String TAG = "ReceipeListActivity";

    private static final String SELECTED_RECEIPE_ID_KEY = "SELECTED_RECEIPE_ID_KEY";
    private List<Receipe> receipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Gson gson = new Gson();
    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        Log.d(TAG, "onCreate: started.");

        recyclerView = findViewById(R.id.my_recycler_view);
        progressbar = findViewById(R.id.progressbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        WsManager wsManager = new WsManager();
        wsManager.sendPostRequest("listRecettes", ReceipeListActivity.this, null);

    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("appFood", errorMessage);
    }

    @Override
    public void onSuccess(String content) {
        Receipe[] founderArray = gson.fromJson(content, Receipe[].class);
        receipeList = Arrays.asList(founderArray);
        Log.d("appFood", receipeList.toString());

        ReceipeListAdapter adapter = new ReceipeListAdapter(receipeList,this, this);
        recyclerView.setAdapter(adapter);
        progressbar.setVisibility(View.GONE);

    }


    public void onClickListener(int position) {
        Receipe receipe = receipeList.get(position);
        Log.d("appFood", "receipe selected :" + receipe);

        Intent intent = new Intent(ReceipeListActivity.this, ReceipeDetailsActivity.class);
        intent.putExtra(SELECTED_RECEIPE_ID_KEY, receipe.getId());
        startActivity(intent);
    }


}



