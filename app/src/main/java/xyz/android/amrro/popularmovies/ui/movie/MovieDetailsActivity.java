package xyz.android.amrro.popularmovies.ui.movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import xyz.android.amrro.popularmovies.R;

public class MovieDetailsActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        final int id = getIntent().getExtras().getInt(MovieDetailsFragment.KEY_MOVIE_ID);
        final MovieDetailsFragment detailsFragment = new MovieDetailsFragment();
        detailsFragment.setMovieId(id);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailsFragment, detailsFragment)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
