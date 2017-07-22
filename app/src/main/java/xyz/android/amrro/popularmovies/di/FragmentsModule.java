package xyz.android.amrro.popularmovies.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.popularmovies.ui.home.HomeFragment;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */
@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    public abstract HomeFragment contributesHomeFragment();
}
