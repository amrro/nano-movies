package xyz.android.amrro.popularmovies.ui.home;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.DataListAdapter;
import xyz.android.amrro.popularmovies.common.OnItemClickedListener;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.databinding.CardMovieBinding;

public final class MoviesAdapter extends DataListAdapter<MovieResult, CardMovieBinding> {

    MoviesAdapter(OnItemClickedListener<MovieResult> listener) {
        super(listener);
    }

    @Override
    protected CardMovieBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        final CardMovieBinding binding = DataBindingUtil.inflate(inflater, R.layout.card_movie, parent, false);
        binding.getRoot().setOnClickListener(view -> {
            final MovieResult movie = binding.getMovie();
            if (movie != null) {
                listener.onClicked(movie);
            }
        });
        return binding;
    }

    @Override
    protected void bind(CardMovieBinding binding, MovieResult item) {
        binding.setMovie(item);
    }
}

