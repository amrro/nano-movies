package xyz.android.amrro.popularmovies.ui.movie.review;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.DataListAdapter;
import xyz.android.amrro.popularmovies.common.OnItemClickedListener;
import xyz.android.amrro.popularmovies.data.model.Review;
import xyz.android.amrro.popularmovies.databinding.ReviewCardBinding;
import xyz.android.amrro.popularmovies.utils.Utils;

public final class ReviewsAdapter extends DataListAdapter<Review, ReviewCardBinding> {

    ReviewsAdapter(OnItemClickedListener<Review> listener) {
        super(listener);
    }

    @Override
    protected ReviewCardBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        final ReviewCardBinding binding = DataBindingUtil.inflate(inflater, R.layout.card_review, parent, false);
        binding.getRoot().setOnClickListener(view -> {
            final Review review = binding.getReview();
            if (review != null) {
                Utils.openLink(view.getContext(), review.url);
            }
        });
        return binding;
    }

    @Override
    protected void bind(ReviewCardBinding binding, Review item) {
        binding.setReview(item);
    }
}
