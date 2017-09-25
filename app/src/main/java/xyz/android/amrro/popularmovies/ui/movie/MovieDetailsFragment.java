package xyz.android.amrro.popularmovies.ui.movie;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.Navigator;
import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.model.Movie;
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
        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        if (getArguments() != null && getArguments().containsKey(Navigator.KEY_ITEM_ID)) {
            Integer movieId = getArguments().getInt(Navigator.KEY_ITEM_ID);
            binding.setNoMovie(false);
            movieViewModel.setMovieId(movieId);
        } else {
            binding.setNoMovie(true);
        }
        movieViewModel.getMovie().observe(this, this::updateMovieUI);
        movieViewModel.isFavorite().observe(this, isFavorite -> {
            if (isFavorite == null) isFavorite = false;
            binding.favoriteFab.setImageResource(isFavorite ? R.drawable.ic_favorite_fill : R.drawable.ic_favorite_empty);
            initFAB();
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
        }
    }

    private void initFAB() {
        // TODO: 7/29/17 animate fab.
        animateFAB();
        binding.favoriteFab.setOnClickListener(view1 ->
                movieViewModel.un_favorite(movie).observe(this, aBoolean -> movieViewModel.retry()));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    private void animateFAB() {
        binding.favoriteFab.setAlpha(0f);
        binding.favoriteFab.setScaleX(0f);
        binding.favoriteFab.setScaleY(0f);

        binding.favoriteFab.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(300)
                .setDuration(1000)
                .setInterpolator(AnimationUtils.loadInterpolator(
                        getContext(),
                        android.R.interpolator.fast_out_slow_in));
    }
}
