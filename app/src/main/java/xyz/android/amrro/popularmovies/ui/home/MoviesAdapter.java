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
import java.util.Objects;

import xyz.android.amrro.popularmovies.common.OnItemClickedListener;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.databinding.CardMovieBinding;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 *
 * Adapter to populate discover results in the {@link HomeFragment}
 */

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    @NonNull
    private final Context context;

    @NonNull
    private ArrayList<MovieResult> data ;

    @NonNull
    private OnItemClickedListener<Integer> listener;

    // each time data is set, we update this variable so that if DiffUtil calculation returns
    // after repetitive updates, we can ignore the old calculation
    private int dataVersion = 0;

    MoviesAdapter(@NonNull Context context,
                  @NonNull ArrayList<MovieResult> data,
                  @NonNull OnItemClickedListener<Integer> listener) {
        Objects.requireNonNull(context, "Context cannot be null for adapter");
        Objects.requireNonNull(listener, "Listener cannot be null");
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final CardMovieBinding binding = CardMovieBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final MovieResult movie = data.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(final ArrayList<MovieResult> update) {
        dataVersion++;

        if (data == null) {
            if (update == null) {
                return;
            }
            data = update;
            notifyDataSetChanged();
        } else if (update == null) {
            int size = data.size();
            data = update;
            notifyItemRangeChanged(0, size);
        } else {
            final int startVersion = dataVersion;
            ArrayList<MovieResult> old = this.data;
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
                    data = update;
                    diffResult.dispatchUpdatesTo(MoviesAdapter.this);
                    notifyDataSetChanged();
                }
            }.execute();
        }
    }

    final class MovieViewHolder extends RecyclerView.ViewHolder {

        private final CardMovieBinding binding;

        MovieViewHolder(CardMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MovieResult movie) {
            binding.getRoot().setOnClickListener(view -> {
                if (movie != null) {
                    listener.onClicked(movie.getId());
                }
            });
            binding.setMovie(movie);
            binding.ratingBar.setRating((float) (movie.getVoteAverage() / 2.0));
            if (movie.getBackdropPath() != null) {
                Glide.with(context)
                        .load(movie.getBackdropPath())
                        .into(binding.backdrop);
            }


        }
    }
}

