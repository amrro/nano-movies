package xyz.android.amrro.popularmovies.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.popularmovies.ui.home.HomeActivity;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */
@Module
public abstract class HomeActivityModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivity();
}
