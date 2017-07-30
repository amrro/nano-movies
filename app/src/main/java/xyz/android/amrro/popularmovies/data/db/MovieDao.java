package xyz.android.amrro.popularmovies.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import xyz.android.amrro.popularmovies.data.model.Movie;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/30/17.
 * <p>
 * Database access object for Movie related operations.
 */

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<Movie> findMovieById(Integer id);

    @Query("DELETE from movie WHERE id = :id")
    void deleteMovieById(Integer id);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * from movie")
    LiveData<List<Movie>> laodAllMovies();
}
