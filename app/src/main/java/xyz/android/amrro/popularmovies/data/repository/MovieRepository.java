package xyz.android.amrro.popularmovies.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;
import xyz.android.amrro.popularmovies.data.model.TrailerResponse;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 *
 * Retrieves all data related to movie.
 */

public final class MovieRepository {

    private final MoviesService api;

    @Inject
    public MovieRepository(@NonNull final MoviesService api) {
        this.api = api;
    }

    public LiveData<ApiResponse<Movie>> movie(@NonNull final Integer id) {
        Objects.requireNonNull(id, "Movie Id CANNOT be null.");
        return api.movie(id);
    }

    public LiveData<ApiResponse<ReviewsResponse>> reviews(@NonNull final Integer id) {
        Objects.requireNonNull(id, "Movie Id CANNOT be null.");
        return api.reviews(id);
    }

    public LiveData<ApiResponse<TrailerResponse>> trailers(@NonNull final Integer id) {
        Objects.requireNonNull(id, "Movie Id CANNOT be null.");
        return api.trailers(id);
    }
}
