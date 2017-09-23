package xyz.android.amrro.popularmovies.ui.movie.trailer;

import android.os.Bundle;

import java.util.List;
import java.util.Objects;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.Navigator;
import xyz.android.amrro.popularmovies.common.SimpleRecyclerFragment;
import xyz.android.amrro.popularmovies.data.model.Trailer;
import xyz.android.amrro.popularmovies.ui.movie.MovieViewModel;
import xyz.android.amrro.popularmovies.utils.Utils;

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
            Utils.openYouTube(getContext(), trailer.key);
        });
    }

    @Override
    protected void updateAdapter() {
        setLoading(true);
        final MovieViewModel movie = getViewModel(MovieViewModel.class);
        movie.setMovieId(itemId()).trailers().observe(this, response -> {
            if (response != null && response.isSuccessful()) {
                final List<Trailer> results = response.getData().getResults();
                if (results != null && results.size() != 0) {
                    adapter.replace(results);
                    setLoading(false);
                } else {
                    setNoData(R.string.no_trailers, R.drawable.ic_local_movies_red_54dp);
                }
            } else {

            }
        });
    }
}
