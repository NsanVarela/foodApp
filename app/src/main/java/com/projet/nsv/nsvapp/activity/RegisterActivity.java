package com.projet.nsv.nsvapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.projet.nsv.nsvapp.R;
import com.projet.nsv.nsvapp.manager.WsManager;
import com.projet.nsv.nsvapp.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements WsManager.Listener {

    private static final String TAG = "nsanvMessage";
    private Gson gson = new Gson();
    private List<User> userList = new ArrayList<>();

    EditText email, password, lastname, firstname;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.i(TAG, "onCreate");

        email = (EditText) findViewById(R.id.etUserNameInput);
        password = (EditText) findViewById(R.id.etPasswordInput);
        lastname = (EditText) findViewById(R.id.etLastNameInput);
        firstname = (EditText) findViewById(R.id.etFirstNameInput);
        signupBtn = (Button) findViewById(R.id.btnSignUp);

        final WsManager wsManager = new WsManager();

        signupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            Map<String, String> params = new HashMap<>();
            params.put("login", email.getText().toString());
            params.put("pass",password.getText().toString());
            params.put("nom",lastname.getText().toString());
            params.put("prenom",firstname.getText().toString());
            wsManager.sendPostRequest("addCompte", RegisterActivity.this, params);
            }
        });

    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, ReceipeListActivity.class);
        startActivity(intent);
    }

    public void onFailure(String toString) {

    }

    public void onSuccess(String s) {
        openRegisterActivity();
    }


}
