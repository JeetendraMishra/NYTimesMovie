package ua.jeet.com.libnytimesmovie.api.reviews;

import ua.jeet.com.libnytimesmovie.model.reviews.Reviews;

public interface ReviewsResponseCallback {

    void onSuccess(Reviews reviews);
    void onFailure(Exception e);
}
