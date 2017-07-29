package xyz.android.amrro.popularmovies.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import xyz.android.amrro.popularmovies.MoviesViewModelFactory;
import xyz.android.amrro.popularmovies.ui.home.DiscoverViewModel;
import xyz.android.amrro.popularmovies.ui.movie.MovieViewModel;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel.class)
    abstract ViewModel bindUserViewModel(DiscoverViewModel discoverViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    abstract ViewModel bindMovieViewModel(MovieViewModel movieViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MoviesViewModelFactory factory);
}
