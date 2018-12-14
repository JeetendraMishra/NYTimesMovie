package ua.jeet.com.libnytimesmovie.api.reviews;

public class ReviewsRequest {

    private final String baseUrl = "https://api.nytimes.com/svc/movies/v2/reviews/";
    private final String apiKey = "d31fe793adf546658bd67e2b6a7fd11a";
    private String resourceType;
    private int offset;
    private String order;

    public ReviewsRequest(String resourceType, int offset, String order) {
        this.resourceType = resourceType;
        this.offset = offset;
        this.order = order;
    }

    public String getUrl(){
        String url = baseUrl;

        if (resourceType != null && !resourceType.trim().equals(""))
            url = url + resourceType + ".json?api-key="+apiKey;
        else
            url = url + "all.json?api-key="+apiKey; // default value of resource type will be 'all'

        if (offset > 0)
            url = url + "&offset="+offset;

        if (order != null && !order.trim().equals(""))
            url = url + "&order="+order;

        return url;
    }
}
