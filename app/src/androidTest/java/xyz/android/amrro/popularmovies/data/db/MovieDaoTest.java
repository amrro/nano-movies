package xyz.android.amrro.popularmovies.data.db;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import xyz.android.amrro.popularmovies.data.model.Movie;

import static com.android.example.github.util.LiveDataTestUtil.getValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/30/17.
 * <p>
 * Tests for {@link MovieDao}.
 */
public class MovieDaoTest {

    // TODO: 7/30/17 add tests
    MoviesDb db;

    public static final String OVERVIEW = "In the near future, a weary Logan cares for an ailing Professor X in a hideout on the Mexican border. But Logan's attempts to hide from the world and his legacy are upended when a young mutant arrives, pursued by dark forces.";
    public static final String TITLE = "Logan";
    public static final String POSTER_PATH = "/poster.jpg";
    public static final Integer ID = 263115;
    public static final String BACKDROP_PATH = "/gGBu0hKw9BGddG8RkRAMX7B6NDB.jpg";

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MoviesDb.class).build();
    }


    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndLoad() throws Exception {
        final Movie logan = createMovie();

        // insert movie to db.
        Integer id = logan.getId();
        db.movieDao().insert(logan);
        final Movie fromDb = getValue(db.movieDao().findMovieById(logan.getId()));

        assertThat(fromDb, notNullValue());
        assertThat(fromDb.getId(), is(logan.getId()));
        assertThat(fromDb.getOverview(), is(logan.getOverview()));
        assertThat(fromDb.getTitle(), is(logan.getTitle()));
        assertThat(fromDb.getPosterPath(), is(logan.getPosterPath()));
        assertThat(fromDb.getBackdropPath(), is(logan.getBackdropPath()));
        assertThat(fromDb.getVoteAverage(), is(logan.getVoteAverage()));
        assertThat(fromDb.getVoteCount(), is(logan.getVoteCount()));

        // insert another and test if it load all.
        logan.setId(6814060);
        db.movieDao().insert(logan);
        List<Movie> movies = getValue(db.movieDao().laodAllMovies());
        assertThat(movies, notNullValue());
        assertThat(movies.size(), is(2));

        // delete movie from db.
        db.movieDao().delete(logan);
        List<Movie> movies1 = getValue(db.movieDao().laodAllMovies());
        assertThat(movies1, notNullValue());
        assertThat(movies1.size(), is(1));

        // delete movie by Id.
        db.movieDao().deleteMovieById(ID);
        List<Movie> emptyMovies = getValue(db.movieDao().laodAllMovies());
        assertThat(emptyMovies, notNullValue());
        assertThat(emptyMovies.size(), is(0));
    }



    private Movie createMovie() throws IOException {
        final Movie logan = new Movie();
        logan.setId(ID);
        logan.setTitle(TITLE);
        logan.setBackdropPath(BACKDROP_PATH);
        logan.setPosterPath(POSTER_PATH);
        logan.setOverview(OVERVIEW);
        logan.setVoteAverage(7.5);
        logan.setVoteCount(4865);
        return logan;
    }
}