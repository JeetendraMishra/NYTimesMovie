package ua.jeet.com.libnytimesmovie.api.critics;

import org.json.JSONObject;

import ua.jeet.com.libnytimesmovie.model.critics.Critics;

public interface CriticsResponseCallback {
    void onSuccess(Critics critics);
    void onFailure(Exception e);
}
