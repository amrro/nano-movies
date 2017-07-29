package xyz.android.amrro.popularmovies.ui.movie;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;
import xyz.android.amrro.popularmovies.R;
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

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.setMovieId(ID);
        movieViewModel.getMovie().observe(this, response -> {

        });

        movieViewModel.getReviews().observe(this, response -> {

        });

        movieViewModel.getTrailers().observe(this, response -> {
            if (response != null && response.isSuccessful()) {
                Timber.d(response.getData().toString());
            } else {
                Timber.d(response.getError().getMessage());
            }
        });
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }
}
