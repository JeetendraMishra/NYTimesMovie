package ua.jeet.com.libnytimesmovie.api.critics;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Critics {

    private Context context;
    private String resourceType;
    private CriticsResponseCallback criticsResponseCallback;

    public Critics(Context context, String resourceType, CriticsResponseCallback criticsResponseCallback) {
        this.context = context;
        this.resourceType = resourceType;
        this.criticsResponseCallback = criticsResponseCallback;
    }

    public void fetchCritics(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String url = new CriticsRequest(resourceType).getUrl();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ua.jeet.com.libnytimesmovie.model.critics.Critics critics = new Gson().fromJson(String.valueOf(response), ua.jeet.com.libnytimesmovie.model.critics.Critics.class);
                    criticsResponseCallback.onSuccess(critics);
                } catch (Exception e) {
                    e.printStackTrace();
                    criticsResponseCallback.onFailure(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                criticsResponseCallback.onFailure(error);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
