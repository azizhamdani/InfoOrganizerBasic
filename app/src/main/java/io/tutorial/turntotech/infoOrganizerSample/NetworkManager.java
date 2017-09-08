package io.tutorial.turntotech.infoOrganizerSample;

/**
 * Created by Webster on 9/5/2017.
 */
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by androidtutorialpoint on 5/11/16.
 */
public class NetworkManager {
    public static String lines[] = null;
    private static NetworkManager sharedInstance;

    private NetworkManager() {}

    public static NetworkManager getSharedInstance(){
        if(sharedInstance == null){
            sharedInstance = new NetworkManager();
        }

        return sharedInstance;
    }

    public void downloadData(final Context context, String urlString, final CallBack callback){
        // Make a network call using volley
        StringRequest stringRequest = new StringRequest(urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse data and update array
                        Log.e("Response",response);
                        lines = response.split("\n");
                        callback.update();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(stringRequest);
    }

}
