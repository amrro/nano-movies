package xyz.android.amrro.popularmovies.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import xyz.android.amrro.popularmovies.data.model.Movie;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/30/17.
 * <p>
 * Database access object for Movie related operations.
 */

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Movie movie);

    @Query("SELECT * FROM " + Movie.TABLE_NAME + " WHERE id = :id")
    Cursor findById(Long id);

    @Query("DELETE from " + Movie.TABLE_NAME + " WHERE id = :id")
    int deleteById(Long id);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * from " + Movie.TABLE_NAME)
    Cursor selectAll();

    @Update
    int update(Movie movie);

    @Query("SELECT COUNT(*) FROM " + Movie.TABLE_NAME)
    int count();
}
