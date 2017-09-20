package xyz.android.amrro.popularmovies.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsFragment;
import xyz.android.amrro.popularmovies.ui.home.HomeFragment;

@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    public abstract HomeFragment contributesHomeFragment();

    @ContributesAndroidInjector
    public abstract MovieDetailsFragment contributesMovieDetailsFragment();
}
