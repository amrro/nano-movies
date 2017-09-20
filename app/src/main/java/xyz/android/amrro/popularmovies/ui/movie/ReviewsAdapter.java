package xyz.android.amrro.popularmovies.ui.movie;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import xyz.android.amrro.popularmovies.data.model.Review;
import xyz.android.amrro.popularmovies.databinding.ItemReviewBinding;

public final class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {
    @NonNull
    private ArrayList<Review> data;

    // each time data is set, we update this variable so that if DiffUtil calculation returns
    // after repetitive updates, we can ignore the old calculation
    private int dataVersion = 0;

    public ReviewsAdapter(@NonNull ArrayList<Review> data) {
        this.data = data;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemReviewBinding binding = ItemReviewBinding.inflate(inflater, parent, false);
        return new ReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        final Review review = data.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }


    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(final ArrayList<Review> update) {
        dataVersion++;

        if (data == null) {
            if (update == null) {
                return;
            }
            data = update;
            notifyDataSetChanged();
        } else if (update == null) {
            final int size = data.size();
            data = update;
            notifyItemRangeChanged(0, size);
        } else {
            int startVersion = this.dataVersion;
            ArrayList<Review> old = this.data;
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
                            return old.get(oldItemPosition).getAuthor().equals(update.get(newItemPosition).getAuthor()) &&
                                    old.get(oldItemPosition).getContent().equals(update.get(newItemPosition).getContent());
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
                    diffResult.dispatchUpdatesTo(ReviewsAdapter.this);
                    notifyDataSetChanged();
                }
            }.execute();
        }
    }

    final class ReviewViewHolder extends RecyclerView.ViewHolder {

        private ItemReviewBinding binding;

        public ReviewViewHolder(@NonNull final ItemReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(@NonNull final Review review) {
            this.binding.setReview(review);
        }
    }

}
