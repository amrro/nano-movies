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
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;

import static com.android.example.github.util.LiveDataTestUtil.getValue;

public class MoviesServiceTest {

    private MoviesService service;

    private MockWebServer server;

    @Before
    public void createServer() {
        Gson gson = new GsonBuilder()
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

    @Test
    public void discover() throws Exception {
//        enqueueResponse("popular.json");
//        final ApiResponse<DiscoverResult> value = getValue(service.discover("popularity.desc"));
//        final RecordedRequest request = server.takeRequest();
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
        enqueueResponse();
        final ApiResponse<ReviewsResponse> value = getValue(service.reviews(263115));
    }


    private void enqueueResponse() throws IOException {
        enqueueResponse("reviews.json", Collections.emptyMap());
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