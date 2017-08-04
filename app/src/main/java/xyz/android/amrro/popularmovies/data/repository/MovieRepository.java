package xyz.android.amrro.popularmovies.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;
import xyz.android.amrro.popularmovies.data.model.TrailerResponse;
import xyz.android.amrro.popularmovies.utils.Utils;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 *
 * Retrieves all data related to movie.
 */

public final class MovieRepository {

    private final Application application;
    private final MoviesService api;

    @Inject
    public MovieRepository(@NonNull final Application application, @NonNull final MoviesService api) {
        this.application = application;
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

    @NonNull
    public LiveData<Boolean> query(@NonNull final Integer movieId) {
        Objects.requireNonNull(movieId, "Cannot query null movie in CP.");

        return new LiveData<Boolean>() {

            @Override
            protected void onActive() {
                super.onActive();
                final String selectionClause = Movie.COLUMN_ID + " = ?";
                final String[] selectionArgs = {movieId.toString()};
                final Cursor cursor = application
                        .getContentResolver()
                        .query(
                                Utils.itemUri(movieId),
                                new String[]{Movie.COLUMN_ID, Movie.COLUMN_TITLE},
                                selectionClause,
                                selectionArgs,
                                null
                        );
            try {
                if (cursor != null) {
                    if (cursor.getCount() == 1) {
                        cursor.moveToFirst();
                        if (Objects.equals(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_ID)), movieId.toString())) {
                            postValue(true);
                        }
                    } else {
                        postValue(false);
                    }
                }
            } finally {
                cursor.close();
            }
            }
        };
    }
}
