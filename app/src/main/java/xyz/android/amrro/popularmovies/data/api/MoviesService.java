package xyz.android.amrro.popularmovies.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */

public interface MoviesService {

    @GET("movie/{movie_id}")
    public Call<Object> movie(@Path("movie_id") String id);

    @GET("movie/{movie_id}/credits")
    public Call<Object> credits(@Path("movie_id") String id);

    @GET("discover/movie")
    public Call<Object> popular(@Query("sort_by") String sort);

}
