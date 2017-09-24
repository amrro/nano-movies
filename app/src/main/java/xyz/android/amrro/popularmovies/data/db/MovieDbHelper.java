package xyz.android.amrro.popularmovies.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import timber.log.Timber;
import xyz.android.amrro.popularmovies.data.db.MoviesContract.MovieEntry;


/**
 * Created by amrro <amr.elghobary@gmail.com> on 9/24/17.
 * A helper class to manage database creation and version management.
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 2;


    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + "("
                + MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MovieEntry.COL_MOVIE_ID + " TEXT NOT NULL, "
                + MovieEntry.COL_POSTER + " TEXT NOT NULL, "
                + MovieEntry.COL_OVERVIEW + " TEXT NOT NULL, "
                + MovieEntry.COL_RELEASE + " TEXT, "
                + MovieEntry.COL_TITLE + " TEXT NOT NULL, "
                + MovieEntry.COL_BACKDROP + " TEXT, "
                + MovieEntry.COL_POPULARITY + " REAL, "
                + MovieEntry.COL_VOTE_AVERAGE + " REAL" + ");"
                + "UNIQUE(" + MovieEntry.COL_MOVIE_ID + ", " + MovieEntry.COL_TITLE + ") ON CONFLICT REPLACE";
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Timber.i("Database version has changed from " + oldVersion + "to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
