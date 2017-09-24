package xyz.android.amrro.popularmovies.data.provider;

import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import xyz.android.amrro.popularmovies.data.db.MovieDaoTest;
import xyz.android.amrro.popularmovies.data.db.MoviesContract.MovieEntry;
import xyz.android.amrro.popularmovies.data.db.MoviesDb;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.utils.Utils;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/31/17.
 * <p>
 * Tests for {@link MoviesContentProvider}.
 */
@SuppressWarnings("ConstantConditions")
public class MoviesContentProviderTest {

    private ContentResolver mContentResolver;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        Room.databaseBuilder(context, MoviesDb.class, "moviesDb").build();
        mContentResolver = context.getContentResolver();
    }


    @Test
    public void movie_initiallyEmpty() {
        /*
         * DB supposedly is empty.
         */
        final Cursor cursor = mContentResolver.query(MovieEntry.CONTENT_URI,
                new String[]{Movie.COLUMN_TITLE}, null, null, null);

        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(0));
        cursor.close();
    }


    @Test
    public void movie_insert() throws Exception {
        final Uri itemUri = mContentResolver.insert(
                MovieEntry.CONTENT_URI,
                Utils.toContentValues(MovieDaoTest.createMovie())
        );

        assertThat(itemUri, notNullValue());

        final Cursor cursor = mContentResolver.query(
                MovieEntry.CONTENT_URI,
                new String[]{Movie.COLUMN_TITLE}, null, null, null
        );


        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(1));
        assertThat(cursor.moveToFirst(), is(true));

        assertThat(cursor.getString(cursor.getColumnIndexOrThrow(Movie.COLUMN_TITLE)), is("Logan"));
        cursor.close();
    }


    @Test
    public void movie_delete() throws Exception {
        final Uri itemUri = mContentResolver.insert(
                MovieEntry.CONTENT_URI,
                Utils.toContentValues(MovieDaoTest.createMovie())
        );

        final Cursor cursor = mContentResolver.query(
                MovieEntry.CONTENT_URI,
                new String[]{Movie.COLUMN_TITLE}, null, null, null
        );


        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(1));
        assertThat(cursor.moveToFirst(), is(true));
        cursor.close();


        final int count = mContentResolver.delete(itemUri, null, null);
        assertThat(count, is(1));

        final Cursor cursor2 = mContentResolver.query(
                MovieEntry.CONTENT_URI,
                new String[]{Movie.COLUMN_TITLE}, null, null, null
        );

        assertThat(cursor2, notNullValue());
        assertThat(cursor2.getCount(), is(0));
        cursor2.close();
    }


}