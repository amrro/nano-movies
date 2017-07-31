package xyz.android.amrro.popularmovies.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.Review;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/30/17.
 * <p>
 * Database to store favorite {@link Movie}s with its corresponding {@link Review}s
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDb extends RoomDatabase {

    public abstract MovieDao movieDao();

}
