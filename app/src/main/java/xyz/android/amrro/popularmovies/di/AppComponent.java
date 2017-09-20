package xyz.android.amrro.popularmovies.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import xyz.android.amrro.popularmovies.MoviesApp;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        NetModule.class,
        ActivitiesModule.class,
        FragmentsModule.class
})
public interface AppComponent {
    void inject(MoviesApp moviesApp);
}
