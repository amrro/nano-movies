package xyz.android.amrro.popularmovies.ui.home;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
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

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.BaseFragment;
import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.db.MoviesContract.MovieEntry;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.databinding.FragmentHomeBinding;
import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsFragment;
import xyz.android.amrro.popularmovies.utils.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    private MoviesAdapter adapter;
    private DiscoverViewModel discover;
    private String filter;
    private Parcelable layoutManagerState;
    private LoaderManager loaderManager;

    private static final int LOADER_MOVIES = 684;
    private static final String KEY_FILTER_ROTATE = "KEY_FILTER_ROTATE";
    private static final String KEY_RECYCLER_POSITION = "KEY_RECYCLER_POSITION";

    @Inject
    CountingIdlingResource idling;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaderManager = getActivity().getSupportLoaderManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
    public void onViewStateRestored(@Nullable Bundle in) {
        super.onViewStateRestored(in);
        if (in != null) {
            setFilter(in.getString(KEY_FILTER_ROTATE, getString(R.string.sort_popularity_desc)));
            layoutManagerState = in.getParcelable(KEY_RECYCLER_POSITION);
            binding.grid.getLayoutManager().onRestoreInstanceState(layoutManagerState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle out) {
        out.putString(KEY_FILTER_ROTATE, filter);
        out.putParcelable(KEY_RECYCLER_POSITION, binding.grid.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(out);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        discover = getViewModel(DiscoverViewModel.class);
        discover.getResults().observe(this, HomeFragment.this::updateAdapter);
        discover.setSort(getString(R.string.sort_popularity_desc));
    }

    private void updateAdapter(final ApiResponse<DiscoverResult> response) {
        if (! idling.isIdleNow())
            idling.decrement();

        if (response != null && response.isSuccessful()) {
            final DiscoverResult result = response.getData();
            final ArrayList<MovieResult> movieResults = result.getResults();
            adapter.replace(movieResults);
            if (layoutManagerState != null) {
                binding.grid.getLayoutManager().onRestoreInstanceState(layoutManagerState);
            }
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
            ((HomeActivity) getActivity()).animateToolbar(getTitle(filter));
            if (filter.equals(getString(R.string.sort_favorites))) {
                loaderManager.initLoader(LOADER_MOVIES, null, loaderCallbacks);
            } else {
                discover.setSort(filter);
            }
        } else {
            binding.setShowLoading(false);
        }
    }


    private void initRecyclerView() {
        if (binding.grid.getLayoutManager() == null) {
            RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.span_count));
            binding.grid.setLayoutManager(manager);
        }

        if (adapter == null) {
            adapter = new MoviesAdapter(getContext(), new ArrayList<>(), id -> {
                final HomeActivity parentActivity = (HomeActivity) getActivity();
                if (parentActivity.isTwoPane) {
                    final MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(id);
                    parentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.details_fragment, fragment, "Two Pane")
                            .commit();
                } else {
                    parentActivity.navigator.toDetails(id);
                }
            });
            binding.grid.setAdapter(adapter);
        }
    }

    private final LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
                    switch (id) {
                        case LOADER_MOVIES:
                            return new CursorLoader(getContext(),
                                    MovieEntry.CONTENT_URI,
                                    new String[]{
                                            MovieEntry.COL_MOVIE_ID,
                                            MovieEntry.COL_TITLE,
                                            MovieEntry.COL_BACKDROP,
                                            MovieEntry.COL_VOTE_AVERAGE,
                                            MovieEntry.COL_RELEASE,
                                            MovieEntry.COL_POSTER,
                                            MovieEntry.COL_OVERVIEW,
                                    },
                                    null, null, null);
                        default:
                            throw new IllegalArgumentException("Supposedly, there is no id other 684");
                    }
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                    switch (loader.getId()) {
                        case LOADER_MOVIES:
                            final ArrayList<MovieResult> favorites = (ArrayList<MovieResult>) Utils.toMoviesResultList(cursor);
                            if (favorites == null || favorites.size() == 0)
                                snack(getString(R.string.prompt_no_fovorites));
                            else adapter.replace(favorites);
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

    @NonNull
    private String getTitle(@NonNull final String filter) {
        String title = getString(R.string.title_popular);
        if (filter.equals(getString(R.string.sort_vot_count_desc)))
            title = getString(R.string.title_top_rated);
        else if (filter.equals(getString(R.string.sort_favorites)))
            title = getString(R.string.title_favorites);
        return title;
    }
}
