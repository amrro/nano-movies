package xyz.android.amrro.popularmovies.ui.home;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;
import xyz.android.amrro.popularmovies.data.model.MovieResult;
import xyz.android.amrro.popularmovies.databinding.FragmentHomeBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends LifecycleFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    MoviesService api;

    private FragmentHomeBinding binding;
    private MoviesAdapter adapter;
    private DiscoverViewModel discoverViewModel;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        discoverViewModel = ViewModelProviders.of(this, viewModelFactory).get(DiscoverViewModel.class);
        discoverViewModel.getResults().observe(this, HomeFragment.this::updateAdapter);
        discoverViewModel.setSort(getString(R.string.sort_popularity_desc));
    }

    public void updateAdapter(final DiscoverResult result) {
        if (result != null) {
            final ArrayList<MovieResult> movieResults = result.getResults();
            if (movieResults != null) {
                adapter.replace(movieResults);
                binding.setShowLoading(false);
            } else {
                adapter.replace(null);
            }
        }
    }

    public interface FilterSelectionListener {
        void onFilterSelected(@NonNull final String filter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                final FiltersButtonSheetFragment sheetFragment = new FiltersButtonSheetFragment();

                sheetFragment.setListener((String filter) -> {
                    binding.setShowLoading(true);
                    discoverViewModel.setSort(filter);
                });
                sheetFragment.show(getFragmentManager(), sheetFragment.getTag());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initRecyclerView() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1);
        binding.grid.setLayoutManager(manager);
//        binding.grid.setHasFixedSize(true);
        adapter = new MoviesAdapter(getContext(), new ArrayList<>());
        binding.grid.setAdapter(adapter);
    }


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
