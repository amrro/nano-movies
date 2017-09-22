package xyz.android.amrro.popularmovies.ui.movie.trailer;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Objects;

import xyz.android.amrro.popularmovies.common.Navigator;
import xyz.android.amrro.popularmovies.common.SimpleRecyclerFragment;
import xyz.android.amrro.popularmovies.data.model.Trailer;
import xyz.android.amrro.popularmovies.ui.movie.MovieViewModel;

public final class TrailersFragment extends SimpleRecyclerFragment<Trailer, TrailersAdapter> {

    public static TrailersFragment newInstance(final Integer id) {
        Bundle args = new Bundle();
        args.putInt(Navigator.KEY_ITEM_ID, Objects.requireNonNull(id));
        TrailersFragment fragment = new TrailersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected TrailersAdapter createAdapter() {
        return new TrailersAdapter(trailer -> {
            // TODO: 9/22/17 view trailer intent
        });
    }

    @Override
    protected void updateAdapter() {
        final MovieViewModel movie = getViewModel(MovieViewModel.class);
        movie.setMovieId(itemId()).trailers().observe(this, response -> {
            if (response != null) {
                final List<Trailer> results = response.getData().getResults();
                if (results != null && results.size() == 0) {
                    adapter.replace(results);
                }
            }
        });
    }
}
