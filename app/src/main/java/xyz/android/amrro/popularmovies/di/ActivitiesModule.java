package xyz.android.amrro.popularmovies.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsActivity;
import xyz.android.amrro.popularmovies.ui.home.HomeActivity;

@Module
public abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivity();

    @ContributesAndroidInjector
    abstract MovieDetailsActivity contributeMovieDetailsActivity();
}
