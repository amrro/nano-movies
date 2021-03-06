package xyz.android.amrro.popularmovies.ui.movie.review;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.Navigator;
import xyz.android.amrro.popularmovies.common.SimpleRecyclerFragment;
import xyz.android.amrro.popularmovies.data.model.Review;
import xyz.android.amrro.popularmovies.ui.movie.MovieViewModel;
import xyz.android.amrro.popularmovies.utils.Utils;

public final class ReviewsFragment extends SimpleRecyclerFragment<Review, ReviewsAdapter> {

    public static ReviewsFragment newInstance(@NonNull final Integer id) {
        Bundle args = new Bundle();
        args.putInt(Navigator.KEY_ITEM_ID, id);
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ReviewsAdapter createAdapter() {
        return new ReviewsAdapter(review -> Utils.openLink(getContext(), review.url));
    }

    @Override
    protected void updateAdapter() {
        final MovieViewModel movie = getViewModel(MovieViewModel.class);
        movie.setMovieId(itemId()).reviews().observe(this, response -> {
            if (response != null && response.isSuccessful()) {
                final List<Review> results = response.getData().getResults();
                if (results != null && results.size() != 0) {
                    adapter.replace(results);
                    setLoading(false);
                } else {
                    setNoData(R.string.no_review, R.drawable.ic_rate_review_black_24dp);
                }
            } else {

            }
        });
    }
}
