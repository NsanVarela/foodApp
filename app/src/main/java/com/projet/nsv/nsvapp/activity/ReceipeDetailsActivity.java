package com.projet.nsv.nsvapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.projet.nsv.nsvapp.R;
import com.projet.nsv.nsvapp.manager.WsManager;
import com.projet.nsv.nsvapp.model.InfoReceipe;
import com.squareup.picasso.Picasso;

public class ReceipeDetailsActivity extends AppCompatActivity implements WsManager.Listener {

    public static final String SELECTED_INFO_RECEIPE_ID_KEY = "SELECTED_INFO_RECEIPE_ID_KEY";
    private RecyclerView recyclerView;
    private Gson gson = new Gson();
    private InfoReceipe foundReceipe;
    private ProgressBar progressBar;

    private Long idReceipe;
    private TextView title;
    private TextView ingredient;
    private ImageView image;
    private RatingBar note;
    private TextView preparation;
    private TextView cookTime;
    private TextView calories;
    private TextView instructions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_details);

        progressBar = findViewById(R.id.progressbar);

        idReceipe = getIntent().getLongExtra(ReceipeListActivity.SELECTED_RECEIPE_ID_KEY, -1);
        title = findViewById(R.id.tv_receipeTitle);
        ingredient = findViewById(R.id.tv_ingredients_list);
        image = findViewById(R.id.iv_receipeImage);
        note = findViewById(R.id.rating);
        preparation = findViewById(R.id.tv_preparation_time);
        cookTime = findViewById(R.id.tv_cook_time);
        calories = findViewById(R.id.tv_calories);
        instructions = findViewById(R.id.tv_instructions_list);

        WsManager wsManager = new WsManager();
        wsManager.sendPostRequest("infoRecette/" + idReceipe, ReceipeDetailsActivity.this, null);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("appRestaurant", errorMessage);
    }

    @Override
    public void onSuccess(String data) {
        foundReceipe = gson.fromJson(data, InfoReceipe.class);
        Log.d("appRestaurant", foundReceipe.toString());

        ingredient.setText(foundReceipe.getIngredients());
        Picasso.get().load(foundReceipe.getPhoto()).into(image);
        title.setText(foundReceipe.getTitle());
        note.setRating(foundReceipe.getNote());
        preparation.setText(String.valueOf(foundReceipe.getTempsPreparation()));
        cookTime.setText(String.valueOf(foundReceipe.getTempsCookRime()));
        calories.setText(String.valueOf(foundReceipe.getCalories()));
        instructions.setText(foundReceipe.getInstructions());

        progressBar.setVisibility(View.GONE);
    }


}
