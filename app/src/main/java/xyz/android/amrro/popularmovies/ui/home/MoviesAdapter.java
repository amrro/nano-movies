package xyz.android.amrro.popularmovies.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import timber.log.Timber;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.databinding.CardMovieBinding;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 */

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    @NonNull
    private final Context context;

    @NonNull
    private ArrayList<MovieResult> movieResults;

    // each time data is set, we update this variable so that if DiffUtil calculation returns
    // after repetitive updates, we can ignore the old calculation
    private int dataVersion = 0;

    public MoviesAdapter(@NonNull Context context, @NonNull ArrayList<MovieResult> movieResults) {
        this.context = context;
        this.movieResults = movieResults;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final CardMovieBinding binding = CardMovieBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final MovieResult movie = movieResults.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(@NonNull final ArrayList<MovieResult> update) {
        dataVersion ++;

        if (movieResults == null) {
            if (update == null) {
                return;
            }
            movieResults = update;
            notifyDataSetChanged();
        } else if (update == null) {
            int size = movieResults.size();
            movieResults = update;
            notifyItemRangeChanged(0, size);
        } else {
            final int startVersion = dataVersion;
            ArrayList<MovieResult> old = this.movieResults;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {

                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return old.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            return old.get(oldItemPosition).getId()
                                    .equals(update.get(newItemPosition).getId());
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            return old.get(oldItemPosition).equals(update.get(newItemPosition));
                        }
                    });
                }

                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    super.onPostExecute(diffResult);

                    if (startVersion != dataVersion) {
                        return;
                    }
                    movieResults = update;
                    diffResult.dispatchUpdatesTo(MoviesAdapter.this);
                    notifyDataSetChanged();
                }
            }.execute();
        }
    }

    public final class MovieViewHolder extends RecyclerView.ViewHolder {

        private final CardMovieBinding binding;

        public MovieViewHolder(CardMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieResult movie) {

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

