# NYTimesMovie

Use libnytimesmovie as a Android library in your any Android application and fetch critics , reviews and search reviews.

using following way: 

public class MainActivity extends AppCompatActivity {

    NYTimesMovie nyTimesMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        nyTimesMovie = NYTimesMovie.getInstance();

//        getCritics();

//        getReviews();

        doSearch();
    }

    private void getReviews() {
        nyTimesMovie.getReviews(this, "all", 0, "", new ReviewsResponseCallback() {
            @Override
            public void onSuccess(Reviews reviews) {
                Toast.makeText(MainActivity.this, "ReviewsResponseCallback onSuccess : "+reviews.getResults().size()+" reviews fetched", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity.this, "ReviewsResponseCallback onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCritics() {
        nyTimesMovie.getCritics(this, "all", new CriticsResponseCallback() {
            @Override
            public void onSuccess(Critics critics) {
                Toast.makeText(MainActivity.this, "CriticsResponseCallback onSuccess : "+critics.getResults().size()+" critics fetched", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity.this, "CriticsResponseCallback onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doSearch() {
        nyTimesMovie.doSearch(this, "Capernaum", "", "", "", "", 0, "", new ReviewsResponseCallback() {
            @Override
            public void onSuccess(Reviews reviews) {
                Toast.makeText(MainActivity.this, "Search ReviewsResponseCallback onSuccess : "+reviews.getResults().size()+" reviews fetched", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity.this, "Search ReviewsResponseCallback onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
