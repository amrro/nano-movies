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
import com.squareup.otto.Bus;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.BaseFragment;
import xyz.android.amrro.popularmovies.common.Navigator;
import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.databinding.FragmentMovieDetailsBinding;
import xyz.android.amrro.popularmovies.utils.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends BaseFragment {
    @Inject
    Bus bus;
    private FragmentMovieDetailsBinding binding;
    private MovieViewModel movieViewModel;
    private Movie movie;

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
        bus.register(this);
        binding.setShowLoading(true);
        movieViewModel = getViewModel(MovieViewModel.class);
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
                    .load(Utils.toPosterFullPath(movie.getPosterPath()))
                    .into(binding.poster);

            Glide.with(this)
                    .asBitmap()
                    .load(Utils.toPosterFullPath(movie.getBackdropPath()))
                    .into(new BitmapImageViewTarget(binding.backdrop) {
                        @Override
                        public void onResourceReady(Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            super.onResourceReady(resource, transition);
                            new Palette.Builder(resource).generate(palette -> {
                                binding.setPalette(palette);
                                final int oldBarColor = getActivity().getWindow().getStatusBarColor();
                                final Palette.Swatch vibrant = palette.getVibrantSwatch();
                                if (vibrant != null) {
                                    bus.post(vibrant);
                                    getActivity().getWindow().setStatusBarColor(vibrant.getRgb());
                                }
                                binding.setShowLoading(false);
                            });
                        }
                    });

            binding.setShowLoading(false);
        }
    }

    private void initFAB() {
        animateFAB();
        binding.favoriteFab.setOnClickListener(view1 ->
                movieViewModel.un_favorite(movie).observe(this, aBoolean -> movieViewModel.retry()));
    }

    private void animateFAB() {
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
