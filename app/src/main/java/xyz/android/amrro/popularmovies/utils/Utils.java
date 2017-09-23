package xyz.android.amrro.popularmovies.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.data.provider.MoviesContentProvider;

import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_BACKDROP;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_ID;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_OVERVIEW;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_POPULARITY;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_POSTER;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_RELEASE;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_TITLE;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_VOTE_AVERAGE;
import static xyz.android.amrro.popularmovies.data.model.Movie.COLUMN_VOTE_COUNT;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/2/17.
 * <p>
 * Helper Methods.
 */

public final class Utils {


    /**
     * Convert {@link Cursor} to a {@link List} of {@link Movie}s.
     */
    public static List<Movie> toMoviesList(@NonNull final Cursor cursor) {
        List<Movie> movies = new ArrayList<>();
        final int idIndex = cursor.getColumnIndex(COLUMN_ID);
        final int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        final int posterIndex = cursor.getColumnIndex(COLUMN_POSTER);
        final int backdropIndex = cursor.getColumnIndex(COLUMN_BACKDROP);
        final int overviewIndex = cursor.getColumnIndex(COLUMN_OVERVIEW);
        final int releaseIndex = cursor.getColumnIndex(COLUMN_RELEASE);
        final int popularityIndex = cursor.getColumnIndex(COLUMN_POPULARITY);
        final int voteAverageIndex = cursor.getColumnIndex(COLUMN_VOTE_AVERAGE);
        final int voteCountIndex = cursor.getColumnIndex(COLUMN_VOTE_COUNT);


        try {
            while (cursor.moveToNext()) {
                movies.add(new Movie(
                        cursor.getInt(idIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(overviewIndex),
                        cursor.getString(backdropIndex),
                        cursor.getString(posterIndex),
                        cursor.getString(releaseIndex),
                        cursor.getDouble(popularityIndex),
                        cursor.getDouble(voteAverageIndex),
                        cursor.getInt(voteCountIndex))
                );
            }

        } finally {
            cursor.close();
        }

        return movies;
    }

    /**
     * Convert {@link Cursor} to a {@link List} of {@link Movie}s.
     */
    public static List<MovieResult> toMoviesResultList(@NonNull final Cursor cursor) {
        List<MovieResult> movies = new ArrayList<>();
        final int idIndex = cursor.getColumnIndex(COLUMN_ID);
        final int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        final int posterIndex = cursor.getColumnIndex(COLUMN_POSTER);
        final int backdropIndex = cursor.getColumnIndex(COLUMN_BACKDROP);
        final int overviewIndex = cursor.getColumnIndex(COLUMN_OVERVIEW);
        final int releaseIndex = cursor.getColumnIndex(COLUMN_RELEASE);
        final int popularityIndex = cursor.getColumnIndex(COLUMN_POPULARITY);
        final int voteAverageIndex = cursor.getColumnIndex(COLUMN_VOTE_AVERAGE);
        final int voteCountIndex = cursor.getColumnIndex(COLUMN_VOTE_COUNT);


        try {
            while (cursor.moveToNext()) {
                movies.add(new MovieResult(
                        cursor.getInt(idIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(overviewIndex),
                        cursor.getString(releaseIndex),
                        cursor.getString(posterIndex),
                        cursor.getString(backdropIndex),
                        cursor.getInt(voteCountIndex),
                        cursor.getDouble(voteAverageIndex),
                        cursor.getDouble(popularityIndex)
                ));
            }

        } finally {
            cursor.close();
        }

        return movies;
    }


    /**
     * Converts {@link ContentValues} into {@link Movie}
     */
    public static Movie fromContentValues(@NonNull final ContentValues values) {
        final Integer id = Objects.requireNonNull(values).containsKey(COLUMN_ID) ? values.getAsInteger(COLUMN_ID) : 0;
        if (id == 0) {
            throw new IllegalArgumentException("id Cannot be null.");
        }

        final String title = values.containsKey(COLUMN_TITLE) ? values.getAsString(COLUMN_TITLE) : "";
        final String release = values.containsKey(COLUMN_RELEASE) ? values.getAsString(COLUMN_RELEASE) : "";
        final String overview = values.containsKey(COLUMN_OVERVIEW) ? values.getAsString(COLUMN_OVERVIEW) : "";
        final String poster = values.containsKey(COLUMN_POSTER) ? values.getAsString(COLUMN_POSTER) : "";
        final String backdrop = values.containsKey(COLUMN_BACKDROP) ? values.getAsString(COLUMN_BACKDROP) : "";
        final Double popularity = values.containsKey(COLUMN_POPULARITY) ? values.getAsDouble(COLUMN_POPULARITY) : 0F;
        final double voteAverage = values.containsKey(COLUMN_VOTE_AVERAGE) ? values.getAsDouble(COLUMN_VOTE_AVERAGE) : 0F;
        final int voteCount = values.containsKey(COLUMN_VOTE_COUNT) ? values.getAsInteger(COLUMN_VOTE_COUNT) : 0;

        return new Movie(id, title, overview, backdrop, poster, release, popularity, voteAverage, voteCount);
    }

    public static String toPosterFullPath(@NonNull final String poster) {
        Objects.requireNonNull(poster, "Poster cannot be null.");

        return String.format("https://image.tmdb.org/t/p/w300%s", poster);
    }


    public static ContentValues toContentValues(@NonNull final Movie movie) {
        final ContentValues values = new ContentValues();
        values.put(COLUMN_ID, movie.getId());
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_RELEASE, movie.getReleaseDate());
        values.put(COLUMN_OVERVIEW, movie.getOverview());
        values.put(COLUMN_POSTER, movie.getPosterPath());
        values.put(COLUMN_BACKDROP, movie.getBackdropPath());
        values.put(COLUMN_POPULARITY, movie.getPopularity());
        values.put(COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(COLUMN_VOTE_COUNT, movie.getVoteCount());
        return values;
    }

    public static Uri itemUri(@NonNull final Integer id) {
        Objects.requireNonNull(id);
        return MoviesContentProvider.URI_MOVIE.buildUpon().appendPath(id.toString()).build();
    }


    public static void shareLink() {

    }
}
