package xyz.android.amrro.popularmovies.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import timber.log.Timber;
import xyz.android.amrro.popularmovies.data.model.Result;
import xyz.android.amrro.popularmovies.databinding.CardMovieBinding;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 */

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    @NonNull
    private final Context context;

    @NonNull
    private ArrayList<Result> results;

    public MoviesAdapter(@NonNull Context context, @NonNull ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final CardMovieBinding binding = CardMovieBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final Result movie = results.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public final class MovieViewHolder extends RecyclerView.ViewHolder {

        private final CardMovieBinding binding;

        public MovieViewHolder(CardMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Result movie) {

            binding.setMovie(movie);
            binding.ratingBar.setRating((float) (movie.getVoteAverage() / 2.0));

            Timber.i(">>>> %s: %s", movie.getTitle(), movie.getBackdropPath());
            if (movie.getBackdropPath() != null) {
                Glide.with(context)
                        .load(movie.getBackdropPath())
                        .into(binding.backdrop);
            }
        }
    }
}

