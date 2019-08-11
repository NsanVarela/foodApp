package com.projet.nsv.nsvapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.projet.nsv.nsvapp.R;
import com.projet.nsv.nsvapp.manager.WsManager;
import com.projet.nsv.nsvapp.model.User;

import java.util.HashMap;
import java.util.Map;

public class SplashScreenActivity extends AppCompatActivity implements WsManager.Listener {

    private static final String TAG = "nsanvMessage";

    RelativeLayout rellay1, rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
        rellay1.setVisibility(View.VISIBLE);
        rellay2.setVisibility(View.VISIBLE);
        }
    };
    EditText name, password;
    TextView infoUser;
    Button btnSignIn, btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        name = (EditText) findViewById(R.id.etUserNameInput);
        password = (EditText) findViewById(R.id.etPasswordInput);
        infoUser = (TextView) findViewById(R.id.tvInfoUser);
        rellay1 = (RelativeLayout) findViewById(R.id.relLay1);
        rellay2 = (RelativeLayout) findViewById(R.id.relLay2);
        handler.postDelayed(runnable, 3000);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
                Animation animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fadein);
                btnRegister.startAnimation(animation);
            }

        });

        final WsManager wsManager = new WsManager();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("login", name.getText().toString());
                params.put("pass",password.getText().toString());
                wsManager.sendPostRequest("connexion", SplashScreenActivity.this, params);

                Animation animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fadein);
                btnSignIn.startAnimation(animation);
            }
        });

    }

    public void openReceipeListActivity(){
        Intent intent = new Intent(this, ReceipeListActivity.class);
        startActivity(intent);
    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /* Sauvegarde les identifiants utilisateur */
    public void saveInfo (View view) {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", name.getText().toString());
        editor.putString("password", password.getText().toString());

        editor.apply();
        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
    }

    /* Affiche les identifiants à l'écran */
    public void displayData (View view) {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sharedPref.getString("username", "");
        String pw = sharedPref.getString("password", "");
        infoUser.setText("Username : " + name + " • Password : " + pw);
    }


    @Override
    public void onFailure(String errorMessage) {
        openRegisterActivity();
    }

    @Override
    public void onSuccess(String s) {
        openReceipeListActivity();
    }
}
