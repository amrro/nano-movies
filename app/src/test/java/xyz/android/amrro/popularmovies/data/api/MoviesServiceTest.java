package xyz.android.amrro.popularmovies.data.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;

import static com.android.example.github.util.LiveDataTestUtil.getValue;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 */
public class MoviesServiceTest {

    private MoviesService service;

    private MockWebServer server;

    private Gson gson;

    @Before
    public void createServer() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        server = new MockWebServer();
        service = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MoviesService.class);
    }

    @After
    public void stopService() throws IOException {
        server.shutdown();
    }
/*

    @Test
    public void movie() throws Exception {
        enqueueResponse("logan.json");
        final Response<Movie> response = service.movie(263115).execute();
        final RecordedRequest request = server.takeRequest();

        assertThat(request.getPath(), is("/movie/263115"));
        assertThat(response.body(), notNullValue());
        assertThat(response.body().getRevenue(), is(616218538));
        assertThat(response.body().getVoteAverage(), is(7.5));
        assertThat(response.body().getVoteCount(), is(4846));
    }

    @Test
    public void credits() throws Exception {
        enqueueResponse("logan_credit.json");
        final Response<Credit> response = service.credits(263115).execute();
        final RecordedRequest request = server.takeRequest();

        assertThat(request.getPath(), is("/movie/263115/credits"));
        assertThat(response.body(), notNullValue());
        assertThat(response.body().getCast().size(), is(104));

        assertThat(response.body().getCast().get(0), notNullValue());
        assertThat(response.body().getCast().get(0).getName(), is("Hugh Jackman"));

        assertThat(response.body().getCast().get(1), notNullValue());
        assertThat(response.body().getCast().get(1).getName(), is("Patrick Stewart"));
    }
*/
    @Test
    public void discover() throws Exception {
        enqueueResponse("popular.json");
        final ApiResponse<DiscoverResult> value = getValue(service.discover("popularity.desc"));
        final RecordedRequest request = server.takeRequest();
/*

        assertThat(request.getPath(), is("/discover/movie?sort_by=popularity.desc"));
        assertThat(response, notNullValue());
*/

        /*assertThat(response.body().getResults().size(), is(20));
        assertThat(response.body().getResults().get(0), notNullValue());
        assertThat(response.body().getResults().get(0).getTitle(), is("Despicable Me 3"));
        assertThat(response.body().getResults().get(0).getPosterPath(), is("/5qcUGqWoWhEsoQwNUrtf3y3fcWn.jpg"));
        assertThat(response.body().getResults().get(0).getPopularity(), is(314.770213));*/
    }

    @Test
    public void reviews() throws Exception {
        enqueueResponse("reviews.json");
        final ApiResponse<ReviewsResponse> value = getValue(service.reviews(263115));
    }



    private void enqueueResponse(String fileName) throws IOException {
        enqueueResponse(fileName, Collections.emptyMap());
    }

    private void enqueueResponse(String fileName, Map<String, String> headers) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("responses/" + fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            mockResponse.addHeader(header.getKey(), header.getValue());
        }
        server.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)));
    }

}