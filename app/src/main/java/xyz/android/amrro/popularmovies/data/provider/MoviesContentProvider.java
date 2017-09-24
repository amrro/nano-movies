package xyz.android.amrro.popularmovies.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import xyz.android.amrro.popularmovies.data.db.MovieDbHelper;
import xyz.android.amrro.popularmovies.data.db.MoviesContract;
import xyz.android.amrro.popularmovies.data.db.MoviesContract.MovieEntry;
import xyz.android.amrro.popularmovies.data.db.MoviesDb;
import xyz.android.amrro.popularmovies.data.model.Movie;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/31/17.
 * This {@link ContentProvider} will wrap the {@link MoviesDb}
 */

public final class MoviesContentProvider extends ContentProvider {
    private static final int CODE_MOVIE_DIR = 11;
    private static final int CODE_MOVIE_ITEM = 22;
    /**
     * The URI matcher.
     */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(MoviesContract.CONTENT_AUTHORITY, Movie.TABLE_NAME, CODE_MOVIE_DIR);
        MATCHER.addURI(MoviesContract.CONTENT_AUTHORITY, Movie.TABLE_NAME + "/#", CODE_MOVIE_ITEM);
    }

    private SQLiteOpenHelper helper;

    @Override
    public boolean onCreate() {
        helper = new MovieDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();
        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR: {
                // return all movies.
                return db.query(MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            }

            case CODE_MOVIE_ITEM: {
                /* return movie with specific _ID  */
                String movieId = MovieEntry.getMovieId(uri);
                return db.query(MovieEntry.TABLE_NAME, projection, MovieEntry.COL_MOVIE_ID + "= ?", new String[]{movieId},
                        null, null, null);
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }


    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR:
                return MovieEntry.CONTENT_TYPE;
            case CODE_MOVIE_ITEM:
                return MovieEntry.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("not valid Uri");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Uri retUri = null;

        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR: {
                long rowId = db.insert(MovieEntry.TABLE_NAME, null, values);
                if (rowId > 0 && values != null)
                    retUri = MovieEntry.buildMovieUri(values.getAsString(MovieEntry.COL_MOVIE_ID));
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (getContext() != null)
            getContext().getContentResolver().notifyChange(uri, null);
        return retUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int deletedRow;

        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR: {
                deletedRow = db.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (deletedRow != 0 && getContext() != null)
            getContext().getContentResolver().notifyChange(uri, null);

        return deletedRow;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updatedRows;
        SQLiteDatabase db = helper.getWritableDatabase();

        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR: {
                updatedRows = db.update(MovieEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (updatedRows != 0 && getContext() != null)
            getContext().getContentResolver().notifyChange(uri, null);
        return updatedRows;
    }
}
