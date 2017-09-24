package xyz.android.amrro.popularmovies.data.db;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 9/24/17.
 * container for constants that define names for URIs, tables, and columns.
 */

public final class MoviesContract {
    public static final String CONTENT_AUTHORITY = "xyz.android.amrro.popularmovies.provider";
    /**
     * The URI for the Movie table.
     */
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + MovieEntry.TABLE_NAME);

    private MoviesContract() {
    }

    @SuppressWarnings("WeakerAccess")
    public static class MovieEntry implements BaseColumns {

        static final String PATH_LOCATION = "movies";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;

        // movie columns
        public static final String COL_MOVIE_ID = "id";
        public static final String TABLE_NAME = "favorite_movies";
        public static final String COL_TITLE = "title";
        public static final String COL_RELEASE = "releaseDate";
        public static final String COL_OVERVIEW = "overview";
        public static final String COL_POSTER = "posterPath";
        public static final String COL_BACKDROP = "backdropPath";
        public static final String COL_POPULARITY = "popularity";
        public static final String COL_VOTE_AVERAGE = "voteAverage";

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMovieUri(String movieId) {
            return CONTENT_URI.buildUpon().appendPath(movieId).build();
        }

        public static String getMovieId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
