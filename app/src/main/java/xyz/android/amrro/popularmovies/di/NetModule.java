package xyz.android.amrro.popularmovies.di;

import android.app.Application;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.AuthInterceptor;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.utils.LiveDataCallAdapterFactory;


@Module
class NetModule {

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Singleton
    @Provides
    OkLogInterceptor provideOkLogInterceptor() {
        return new OkLogInterceptor.Builder()
                .shortenInfoUrl(true)
                .withAllLogData()
                .useAndroidLog(true)
                .build();
    }


    @Singleton
    @Provides
    AuthInterceptor provideAuthInterceptor(Application app) {
        return new AuthInterceptor(app);
    }


    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkLogInterceptor logInterceptor, AuthInterceptor auth) {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(auth)
                .addInterceptor(logInterceptor)
                .build();
    }

    @Singleton
    @Provides
    MoviesService provideMoviesService(OkHttpClient client, Gson gson, Application app) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .baseUrl(app.getString(R.string.api_url))
                .client(client)
                .build()
                .create(MoviesService.class);
    }
}
