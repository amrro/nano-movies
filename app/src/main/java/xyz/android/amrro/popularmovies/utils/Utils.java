package xyz.android.amrro.popularmovies.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import xyz.android.amrro.popularmovies.data.db.MoviesContract.MovieEntry;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.data.model.Trailer;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/2/17.
 * <p>
 * Helper Methods.
 */

public final class Utils {
    private Utils() {
    }

    /**
     * Convert {@link Cursor} to a {@link List} of {@link Movie}s.
     */
    public static List<MovieResult> toMoviesResultList(@NonNull final Cursor cursor) {
        List<MovieResult> movies = new ArrayList<>();
        final int idIndex = cursor.getColumnIndex(MovieEntry.COL_MOVIE_ID);
        final int titleIndex = cursor.getColumnIndex(MovieEntry.COL_TITLE);
        final int posterIndex = cursor.getColumnIndex(MovieEntry.COL_POSTER);
        final int backdropIndex = cursor.getColumnIndex(MovieEntry.COL_BACKDROP);
        final int overviewIndex = cursor.getColumnIndex(MovieEntry.COL_OVERVIEW);
        final int releaseIndex = cursor.getColumnIndex(MovieEntry.COL_RELEASE);
        final int voteAverageIndex = cursor.getColumnIndex(MovieEntry.COL_VOTE_AVERAGE);


        try {
            while (cursor.moveToNext()) {
                movies.add(new MovieResult(
                        cursor.getInt(idIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(overviewIndex),
                        cursor.getString(releaseIndex),
                        cursor.getString(posterIndex),
                        cursor.getString(backdropIndex),
                        0,
                        cursor.getDouble(voteAverageIndex),
                        0f
                ));
            }

        } finally {
            cursor.close();
        }
        return movies;
    }


    public static String toPosterFullPath(@NonNull final String poster) {
        Objects.requireNonNull(poster, "Poster cannot be null.");

        return String.format("https://image.tmdb.org/t/p/w300%s", poster);
    }


    public static ContentValues toContentValues(@NonNull final Movie movie) {
        final ContentValues values = new ContentValues();
        values.put(MovieEntry.COL_MOVIE_ID, movie.getId());
        values.put(MovieEntry.COL_TITLE, movie.getTitle());
        values.put(MovieEntry.COL_RELEASE, movie.getReleaseDate());
        values.put(MovieEntry.COL_OVERVIEW, movie.getOverview());
        values.put(MovieEntry.COL_POSTER, movie.getPosterPath());
        values.put(MovieEntry.COL_BACKDROP, movie.getBackdropPath());
        values.put(MovieEntry.COL_POPULARITY, movie.getPopularity());
        values.put(MovieEntry.COL_VOTE_AVERAGE, movie.getVoteAverage());
        return values;
    }

    public static Uri itemUri(@NonNull final Integer id) {
        Objects.requireNonNull(id);
        return MovieEntry.CONTENT_URI.buildUpon().appendPath(id.toString()).build();
    }


    public static void shareYouTube(final Context context, @NonNull final String key) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        share.putExtra(Intent.EXTRA_SUBJECT, "Watch this Trailer");
        share.putExtra(Intent.EXTRA_TEXT, Trailer.buildYouTubeLink(key));
        context.startActivity(Intent.createChooser(share, "Share link!"));
    }

    public static void openYouTube(final Context context, @NonNull final String key) {
        final String link = Trailer.buildYouTubeLink(key);
        openLink(context, link);
    }

    public static void openLink(final Context context, @NonNull final String link) {
        Intent share = new Intent(Intent.ACTION_VIEW);
        share.setData(Uri.parse(link));
        context.startActivity(share);
    }

}
