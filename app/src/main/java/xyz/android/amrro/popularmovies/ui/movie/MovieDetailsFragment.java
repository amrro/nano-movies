package xyz.android.amrro.popularmovies.ui.movie;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.Review;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;
import xyz.android.amrro.popularmovies.databinding.FragmentMovieDetailsBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends LifecycleFragment {

    public static final int ID = 263115;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    FragmentMovieDetailsBinding binding;
    private MovieViewModel movieViewModel;
    private ReviewsAdapter adapter;

    public MovieDetailsFragment() {
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
        initRecyclerView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.setMovieId(ID);
        movieViewModel.getMovie().observe(this, this::updateMovieUI);
        movieViewModel.getReviews().observe(this, this::updateReviews);
        movieViewModel.getTrailers().observe(this, response -> {
        });
    }

    private void updateMovieUI(@NonNull final ApiResponse<Movie> response) {
        if (response.isSuccessful()) {
            final Movie movie = response.getData();
            binding.setMovie(movie);
            Glide.with(this)
                    .load(movie.getBackdropPath())
                    .into(binding.backdrop);

            Glide.with(this)
                    .load(movie.getPosterPath())
                    .into(binding.poster);

            binding.setShowLoading(false);
            animateFAB();
        }
    }

    private void updateReviews(@NonNull final ApiResponse<ReviewsResponse> response) {
        if (response.isSuccessful()) {
            List<Review> reviews = response.getData().getResults();
            if (reviews.size() > 0) {
                adapter.replace((ArrayList<Review>) reviews);
            } else {
                adapter.replace(null);
                binding.setNoReviews(true);
            }
        }
    }

    private void animateFAB() {
        // TODO: 7/29/17 animate fab.
        // TODO: 7/29/17 change icon.
    }

    public void initRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        binding.reviews.setLayoutManager(manager);
        adapter = new ReviewsAdapter(getContext(), null);
        binding.reviews.setAdapter(adapter);

        /*binding.grid.setLayoutManager(manager);
//        binding.grid.setHasFixedSize(true);
        adapter = new MoviesAdapter(getContext(), new ArrayList<>());
        binding.grid.setAdapter(adapter);*/
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }
}