package xyz.android.amrro.popularmovies.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/27/17.
 */

@Singleton
public final class DiscoverRepository {
    @NonNull
    private final MoviesService moviesService;


    @Inject
    public DiscoverRepository(@NonNull final MoviesService service) {
        this.moviesService = service;
    }


    @NonNull
    public LiveData<DiscoverResult> discover(@NonNull String sorting) {
        return new LiveData<DiscoverResult>() {
            @Override
            protected void onActive() {
                super.onActive();
                moviesService.discover(sorting).enqueue(
                        new Callback<DiscoverResult>() {
                            @Override
                            public void onResponse(Call<DiscoverResult> call, Response<DiscoverResult> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    postValue(response.body());
                                    Timber.d(">>>>%s", response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<DiscoverResult> call, Throwable t) {
                                Timber.e(">>>> error getting Discover: %s", t.getMessage());

                                // TODO E: >>>> error getting Discover: timeout
                            }
                        }
                );
            }
        };

    }

}
