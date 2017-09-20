package xyz.android.amrro.popularmovies.ui.home;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.data.provider.MoviesContentProvider;
import xyz.android.amrro.popularmovies.databinding.FragmentHomeBinding;
import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsActivity;
import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsFragment;
import xyz.android.amrro.popularmovies.utils.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentHomeBinding binding;
    private MoviesAdapter adapter;
    private DiscoverViewModel discoverViewModel;

    private static final int LOADER_MOVIES = 684;
    public static final String KEY_ROTATE = "KEY_ROTATE";

    private String filter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding.setShowLoading(true);
        initRecyclerView();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            setFilter(savedInstanceState.getString(KEY_ROTATE, getString(R.string.sort_popularity_desc)));
        } else if (filter == null) {
            filter = getString(R.string.sort_popularity_desc);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_ROTATE, filter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        discoverViewModel = ViewModelProviders.of(this, viewModelFactory).get(DiscoverViewModel.class);
        discoverViewModel.getResults().observe(this, HomeFragment.this::updateAdapter);
        discoverViewModel.setSort(getString(R.string.sort_popularity_desc));
    }

    public void updateAdapter(final ApiResponse<DiscoverResult> response) {
        if (response != null && response.isSuccessful()) {
            final DiscoverResult result = response.getData();
            final ArrayList<MovieResult> movieResults = result.getResults();
            adapter.replace(movieResults);
            binding.setShowLoading(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                final FiltersButtonSheetFragment sheetFragment = new FiltersButtonSheetFragment();
                sheetFragment.setListener(this::setFilter);
                sheetFragment.show(getFragmentManager(), sheetFragment.getTag());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setFilter(String newFilter) {
        binding.setShowLoading(true);
        if (! Objects.equals(this.filter, newFilter)) {
            this.filter = newFilter;
            if (filter.equals(getString(R.string.sort_favorites))) {
                getActivity().getSupportLoaderManager()
                        .initLoader(LOADER_MOVIES, null, loaderCallbacks);
            } else {
                discoverViewModel.setSort(filter);
            }
        }
    }


    public void initRecyclerView() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1);
        binding.grid.setLayoutManager(manager);
        adapter = new MoviesAdapter(getContext(), new ArrayList<>(), id -> {
            final Intent toDetailsIntent = new Intent(getContext(), MovieDetailsActivity.class);
            toDetailsIntent.putExtra(MovieDetailsFragment.KEY_MOVIE_ID, id);
            startActivity(toDetailsIntent);
        });
        binding.grid.setAdapter(adapter);
    }

    private LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
                    switch (id) {
                        case LOADER_MOVIES:
                            return new CursorLoader(getContext(),
                                    MoviesContentProvider.URI_MOVIE,
                                    new String[]{Movie.COLUMN_ID, Movie.COLUMN_TITLE, Movie.COLUMN_BACKDROP, Movie.COLUMN_VOTE_AVERAGE},
                                    null, null, null);
                        default:
                            throw new IllegalArgumentException("Supposedly, there is no id other 684");
                    }
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                    switch (loader.getId()) {
                        case LOADER_MOVIES:
                            adapter.replace((ArrayList<MovieResult>) Utils.toMoviesResultList(cursor));
                            break;
                    }
                    binding.setShowLoading(false);
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_MOVIES:
                            adapter.replace(null);
                    }
                }
            };


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        adapter = null;
    }
}
