package xyz.android.amrro.popularmovies.di;

import android.support.annotation.NonNull;

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
import xyz.android.amrro.popularmovies.data.AuthInterceptor;
import xyz.android.amrro.popularmovies.data.api.MoviesService;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */
@Module
public class NetModule {

    @NonNull
    private String url = "https://api.themoviedb.org/3/";

//    public NetModule(@NonNull String url) {
//        this.url = url;
//    }


    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Singleton
    @Provides
    public OkLogInterceptor provideOkLogInterceptor() {
        return new OkLogInterceptor.Builder()
                .shortenInfoUrl(true)
                .withAllLogData()
                .useAndroidLog(true)
                .build();
    }


    @Singleton
    @Provides
    public AuthInterceptor provideAuthInterceptor() {
        return new AuthInterceptor();
    }


    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(OkLogInterceptor logInterceptor, AuthInterceptor auth) {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(auth)
                .addInterceptor(logInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public MoviesService provideMoviesService(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(url)
                .client(client)
                .build()
                .create(MoviesService.class);
    }
}
