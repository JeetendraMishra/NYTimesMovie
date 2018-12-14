package ua.jeet.com.libnytimesmovie.api.search;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import ua.jeet.com.libnytimesmovie.api.reviews.ReviewsResponseCallback;

public class Search {

    private Context context;
    private String query;
    private String criticsPick;
    private String reviewer;
    private String publicationDate;
    private String openingDate;
    private int offset;
    private String order;
    private ReviewsResponseCallback reviewsResponseCallback;

    public Search(Context context, String query, String criticsPick, String reviewer, String publicationDate, String openingDate, int offset, String order, ReviewsResponseCallback reviewsResponseCallback) {
        this.context = context;
        this.query = query;
        this.criticsPick = criticsPick;
        this.reviewer = reviewer;
        this.publicationDate = publicationDate;
        this.openingDate = openingDate;
        this.offset = offset;
        this.order = order;
        this.reviewsResponseCallback = reviewsResponseCallback;
    }

    public void doSearch(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String url = new SearchRequest(query, criticsPick, reviewer, publicationDate, openingDate, offset, order).getUrl();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ua.jeet.com.libnytimesmovie.model.reviews.Reviews reviews = new Gson().fromJson(String.valueOf(response), ua.jeet.com.libnytimesmovie.model.reviews.Reviews.class);
                    reviewsResponseCallback.onSuccess(reviews);
                } catch (Exception e) {
                    e.printStackTrace();
                    reviewsResponseCallback.onFailure(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                reviewsResponseCallback.onFailure(error);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
