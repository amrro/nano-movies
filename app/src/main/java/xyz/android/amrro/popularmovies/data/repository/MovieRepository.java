package xyz.android.amrro.popularmovies.data.repository;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import xyz.android.amrro.popularmovies.data.api.MoviesService;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 */

public final class MovieRepository {

    private final MoviesService api;

    @Inject
    public MovieRepository(@NonNull final MoviesService api) {
        this.api = api;
    }
}
