package xyz.android.amrro.popularmovies.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import xyz.android.amrro.popularmovies.data.model.Review;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/30/17.
 * <p>
 * Database access object for Review related operations.
 */

@Dao
public interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Review review);


//    @Query("DELETE from review WHERE id = :id")
//    void delete(String id);

    @Delete
    void delete(Review review);

    @Query("SELECT * FROM review WHERE id = :id")
    LiveData<Review> findReviewById(String id);

    @Query("SELECT * from review")
    LiveData<List<Review>> loadAllReviews();
}
