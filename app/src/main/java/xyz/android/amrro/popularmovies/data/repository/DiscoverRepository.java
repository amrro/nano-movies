package xyz.android.amrro.popularmovies.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;
import xyz.android.amrro.popularmovies.ui.home.HomeFragment;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/27/17.
 * <p>
 * This Repository is solely responsible for loading the discover results in the {@link HomeFragment}
 */

@Singleton
public final class DiscoverRepository {
    @NonNull
    private final MoviesService moviesService;


    @Inject
    DiscoverRepository(@NonNull final MoviesService service) {
        this.moviesService = service;
    }


    @NonNull
    public LiveData<ApiResponse<DiscoverResult>> discover(@NonNull String sorting) {
        return moviesService.discover(sorting);
    }

}
