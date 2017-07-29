package xyz.android.amrro.popularmovies.data.api;

import android.arch.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * <p>
 * The Gate Point for all movies db APIs.
 */

public interface MoviesService {

    @GET("discover/movie")
    LiveData<ApiResponse<DiscoverResult>> discover(@Query("sort_by") String sort);

    @GET("movie/{movie_id}")
    Call<Movie> movie(@Path("movie_id") int id);

    @GET("movie/{movie_id}/reviews")
    LiveData<ApiResponse<ReviewsResponse>> reviews(@Path("movie_id") int movieId);

}
