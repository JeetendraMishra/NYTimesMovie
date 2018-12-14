package ua.jeet.com.libnytimesmovie.api.critics;

public class CriticsRequest {

    private final String baseUrl = "https://api.nytimes.com/svc/movies/v2/critics/";
    private final String apiKey = "d31fe793adf546658bd67e2b6a7fd11a";
    private String resourceType;

    public CriticsRequest(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl(){
        String url = baseUrl;

        if (resourceType != null && !resourceType.trim().equals(""))
            url = url + resourceType + ".json?api-key="+apiKey;
        else
            url = url + "all.json?api-key="+apiKey; // default value of resource type will be 'all'

        return url;
    }

}