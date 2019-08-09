package com.projet.nsv.nsvapp.manager;

import android.util.Log;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class WsManager {

    private static final String TAG = "nsanvMessage";
    public static final String URL = "http://51.15.254.4:9001/ws/resto/";

    public void sendPostRequest(String target, final Listener listener, Map<String, String> params) {

        if(params == null){
            params = new HashMap<>();
        }

        String jsonString = new Gson().toJson(params);
        Log.d(TAG, "sendPostRequest() called with: target = [" + target + "], listener = [" + listener + "], params = [" + params + "]");

        Map<String, String> header = new HashMap<>();

        header.put("Content-Type","application/json");

        Fuel.post(URL + target).body(jsonString, Charset.forName("UTF-8")).header(header).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError fuelError) {
                Log.e("3WResto","error : " + fuelError);
                listener.onFailure(fuelError.toString());
            }

            @Override
            public void success(Request request, Response response, String s) {
                Log.d("3WResto","success : " + s);
                listener.onSuccess(s);
            }

        });

    }



    public interface Listener {

        void onFailure(String errorMessage);

        void onSuccess(String content);
    }
}

