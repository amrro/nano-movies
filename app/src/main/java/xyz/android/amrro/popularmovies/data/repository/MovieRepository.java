package xyz.android.amrro.popularmovies.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.db.MoviesContract.MovieEntry;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;
import xyz.android.amrro.popularmovies.data.model.TrailerResponse;
import xyz.android.amrro.popularmovies.utils.Utils;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 * <p>
 * Retrieves all data related to movie.
 */

public final class MovieRepository {

    private final Application application;
    private final MoviesService api;

    @Inject
    MovieRepository(@NonNull final Application application, @NonNull final MoviesService api) {
        this.application = application;
        this.api = api;
    }

    public LiveData<ApiResponse<Movie>> movie(@NonNull final Integer id) {
        return api.movie(Objects.requireNonNull(id));
    }

    public LiveData<ApiResponse<ReviewsResponse>> reviews(@NonNull final Integer id) {
        return api.reviews(Objects.requireNonNull(id));
    }

    public LiveData<ApiResponse<TrailerResponse>> trailers(@NonNull final Integer id) {
        return api.trailers(Objects.requireNonNull(id));
    }

    @NonNull
    public LiveData<Boolean> query(@NonNull final Integer movieId) {
        Objects.requireNonNull(movieId);

        return new LiveData<Boolean>() {

            @Override
            protected void onActive() {
                super.onActive();
                final String selectionClause = MovieEntry.COL_MOVIE_ID + " = ?";
                final String[] selectionArgs = {movieId.toString()};
                final Cursor cursor = application
                        .getContentResolver()
                        .query(MovieEntry.buildMovieUri(movieId),
                                new String[]{MovieEntry.COL_MOVIE_ID, MovieEntry.COL_TITLE},
                                selectionClause,
                                selectionArgs,
                                null
                        );
                try {
                    if (cursor != null) {
                        if (cursor.getCount() == 1) {
                            cursor.moveToFirst();
                            postValue(Objects.equals(cursor.getString(cursor.getColumnIndex(MovieEntry.COL_MOVIE_ID)), movieId.toString()));

                        } else {
                            postValue(false);
                        }
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        };
    }


    @NonNull
    public LiveData<Boolean> toggleFavorite(@NonNull final Movie movie) {
        return Transformations.switchMap(this.query(Objects.requireNonNull(movie).getId()), isFavorite -> {
            return isFavorite ? this.unfavorite(movie) : this.doFavorite(movie);
        });
    }

    @NonNull
    private LiveData<Boolean> doFavorite(@NonNull final Movie movie) {
        Objects.requireNonNull(movie);

        return new MutableLiveData<Boolean>() {
            @Override
            protected void onActive() {
                super.onActive();
                final Uri uri = application.getContentResolver().insert(
                        MovieEntry.CONTENT_URI,
                        Utils.toContentValues(movie)
                );

                postValue(uri != null && uri.getLastPathSegment().equals(movie.getId().toString()));
            }
        };
    }

    @NonNull
    private LiveData<Boolean> unfavorite(@NonNull final Movie movie) {
        Objects.requireNonNull(movie);
        return new LiveData<Boolean>() {
            @Override
            protected void onActive() {
                super.onActive();
                final int count = application.getContentResolver().delete(
                        Utils.itemUri(movie.getId()),
                        MovieEntry.COL_MOVIE_ID + "=?",
                        new String[]{String.valueOf(movie.getId())}
                );
                postValue(count == 1);
            }
        };
    }
}
