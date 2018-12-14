package ua.jeet.com.libnytimesmovie;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import ua.jeet.com.libnytimesmovie.api.critics.Critics;
import ua.jeet.com.libnytimesmovie.api.critics.CriticsResponseCallback;
import ua.jeet.com.libnytimesmovie.api.reviews.Reviews;
import ua.jeet.com.libnytimesmovie.api.reviews.ReviewsResponseCallback;
import ua.jeet.com.libnytimesmovie.api.reviews.ReviewsRequest;
import ua.jeet.com.libnytimesmovie.api.search.Search;

public class NYTimesMovie {

    private static volatile NYTimesMovie nyTimesMovie;

    private NYTimesMovie(){
        if (nyTimesMovie != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static NYTimesMovie getInstance() {
        if (nyTimesMovie == null) {
            synchronized (NYTimesMovie.class) {
                if (nyTimesMovie == null) nyTimesMovie = new NYTimesMovie();
            }
        }

        return nyTimesMovie;
    }

    /**
     *
     * @param context pass the context
     * @param resourceType resource type can be 'all or full-time or part-time or any reviewer name' ; you can send null or empty as well then by default resource type will be all
     * @param criticsResponseCallback implement ReviewsResponseCallback to get result back in format of Critics object
     */
    public void getCritics(Context context, String resourceType, CriticsResponseCallback criticsResponseCallback){
        new Critics(context, resourceType, criticsResponseCallback).fetchCritics();
    }

    /**
     *
     * @param context pass the context
     * @param resourceType resource type can be 'all or picks' ; you can send null or empty as well then by default resource type will be all
     * @param offset offset can be positive integer, multiple of 20 ; you can send 0 as well
     * @param order order can be 'by-title or by-publication-date or by-opening-date' ; you can send null or empty as well then by default results will be ordered by publication-date
     * @param reviewsResponseCallback implement ReviewsResponseCallback to get result back in format of Reviews object
     */
    public void getReviews(Context context, String resourceType, int offset, String order, ReviewsResponseCallback reviewsResponseCallback){
        new Reviews(context, resourceType, offset, order, reviewsResponseCallback).fetchReviews();
    }

    /**
     *
     * @param context pass the context
     * @param query is a Search keywords; matches movie title and indexed terms. If you omit the query parameter, your request will be equivalent to a reviews and NYT Critics' Picks request ; you can send null or empty as well
     * @param criticsPick Set this parameter to Y to limit the results to NYT Critics' Picks. To get only those movies that have not been highlighted by Times critics, specify critics-pick=N. (To get all reviews regardless of critics-pick status, simply omit this parameter.) ; you can send null or empty as well
     * @param reviewer Include this parameter to limit your results to reviews by a specific critic. Reviewer names should be formatted like this: Manohla Dargis. ; you can send null or empty as well
     * @param publicationDate The publication-date is the date the review was first published in The Times in this format YYYY-MM-DD ; you can send null or empty as well
     * @param openingDate The opening-date is the date the movie's opening date in the New York region in this format YYYY-MM-DD ; you can send null or empty as well
     * @param offset offset can be positive integer, multiple of 20 ; you can send 0 as well
     * @param order Sets the sort order of the results. Results ordered by-title are in ascending alphabetical order. Results ordered by one of the date parameters are in reverse chronological order. ; you can send null or empty as well then by default results will be ordered by publication-date
     * @param reviewsResponseCallback implement ReviewsResponseCallback to get search result back in format of Reviews object
     */
    public void doSearch(Context context, String query, String criticsPick, String reviewer, String publicationDate, String openingDate, int offset, String order, ReviewsResponseCallback reviewsResponseCallback){
        new Search(context, query, criticsPick, reviewer, publicationDate, openingDate, offset, order, reviewsResponseCallback).doSearch();
    }
}
