package xyz.android.amrro.popularmovies.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import xyz.android.amrro.popularmovies.MoviesViewModelFactory;
import xyz.android.amrro.popularmovies.ui.home.DiscoverViewModel;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel.class)
    abstract ViewModel bindUserViewModel(DiscoverViewModel discoverViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MoviesViewModelFactory factory);
}
