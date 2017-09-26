package xyz.android.amrro.popularmovies.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.test.espresso.idling.CountingIdlingResource;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @NonNull
    private final Application application;

    public AppModule(@NonNull Application app) {
        this.application = app;
    }


    @Singleton
    @Provides
    Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(@NonNull Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    CountingIdlingResource providesCountingIdlingResource() {
        return new CountingIdlingResource("ESPRESSO");
    }

    @Singleton
    @Provides
    Bus providesBus() {
        return new Bus("MOVIES");
    }
}
