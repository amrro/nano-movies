package xyz.android.amrro.popularmovies.ui.movie;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.Navigator;
import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.Review;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;
import xyz.android.amrro.popularmovies.databinding.FragmentMovieDetailsBinding;
import xyz.android.amrro.popularmovies.utils.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {
    private Movie movie;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentMovieDetailsBinding binding;
    private MovieViewModel movieViewModel;

    public MovieDetailsFragment() {
    }

    public static MovieDetailsFragment newInstance(@NonNull final Integer id) {
        Bundle args = new Bundle();
        args.putInt(Navigator.KEY_ITEM_ID, Objects.requireNonNull(id));
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setShowLoading(true);
        binding.favoriteFab.setOnClickListener(view1 ->
                movieViewModel.un_favorite(movie).observe(this, aBoolean -> movieViewModel.retry()));
//        initRecyclerView();
        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        if (getArguments() != null && getArguments().containsKey(Navigator.KEY_ITEM_ID)) {
            Integer movieId = getArguments().getInt(Navigator.KEY_ITEM_ID);
            binding.setNoMovie(false);
            movieViewModel.setMovieId(movieId);
        } else {
            binding.setNoMovie(true);
        }

        movieViewModel.getMovie().observe(this, this::updateMovieUI);
//        movieViewModel.getReviews().observe(this, this::updateReviews);

        movieViewModel.isFavorite().observe(this, isFavorite -> {
            binding.favoriteFab.setImageResource(isFavorite ? R.drawable.ic_favorite_fill : R.drawable.ic_favorite_empty);
        });
    }

    private void updateMovieUI(@NonNull final ApiResponse<Movie> response) {
        if (response.isSuccessful()) {
            movie = response.getData();
            binding.setMovie(movie);
            Glide.with(this)
                    .load(Utils.toPosterFullPath(movie.getBackdropPath()))
                    .into(binding.backdrop);

            Glide.with(this)
                    .load(Utils.toPosterFullPath(movie.getPosterPath()))
                    .into(binding.poster);

            binding.setShowLoading(false);
            initFAB();
        }
    }

    private void initFAB() {
        // TODO: 7/29/17 animate fab.
        // TODO: 7/29/17 change icon.
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }
}
