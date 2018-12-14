package ua.jeet.com.libnytimesmovie.api.search;

public class SearchRequest {
    private final String baseUrl = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
    private final String apiKey = "d31fe793adf546658bd67e2b6a7fd11a";
    private String query;
    private String criticsPick;
    private String reviewer;
    private String publicationDate;
    private String openingDate;
    private int offset;
    private String order;

    public SearchRequest(String query, String criticsPick, String reviewer, String publicationDate, String openingDate, int offset, String order) {
        this.query = query;
        this.criticsPick = criticsPick;
        this.reviewer = reviewer;
        this.publicationDate = publicationDate;
        this.openingDate = openingDate;
        this.offset = offset;
        this.order = order;
    }

    public String getUrl(){
        String url = baseUrl;

        url = url + "?api-key="+apiKey;

        if (query != null && !query.trim().equals(""))
            url = url + "&query="+query;

        if (criticsPick != null && !criticsPick.trim().equals(""))
            url = url + "&critics-pick="+criticsPick;

        if (reviewer != null && !reviewer.trim().equals(""))
            url = url + "&reviewer="+reviewer;

        if (publicationDate != null && !publicationDate.trim().equals(""))
            url = url + "&publication-date="+publicationDate;

        if (openingDate != null && !openingDate.trim().equals(""))
            url = url + "&opening-date="+openingDate;

        if (offset > 0)
            url = url + "&offset="+offset;

        if (order != null && !order.trim().equals(""))
            url = url + "&order="+order;

        return url;
    }
}
