package xyz.android.amrro.popularmovies.ui.home;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.model.Result;
import xyz.android.amrro.popularmovies.data.model.Search;
import xyz.android.amrro.popularmovies.databinding.FragmentHomeBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    @Inject
    MoviesService api;

    private FragmentHomeBinding binding;

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
        initRecyclerView();
        getResults(getString(R.string.sort_popularity_desc));
    }


    public interface FilterSelectionListener {
        void onFilterSelected(final String filter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                final FiltersButtonSheetFragment sheetFragment = new FiltersButtonSheetFragment();

                sheetFragment.setListener((String filter) -> {
                    Timber.i(">>>> selectedFilter: %s", filter);
                    binding.setShowLoading(false);
                    getResults(filter);
                });
                sheetFragment.show(getFragmentManager(), sheetFragment.getTag());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void getResults(final String filter) {
        if (api != null) {
            api.discover(filter).enqueue(
                    new Callback<Search>() {
                        @Override
                        public void onResponse(Call<Search> call, Response<Search> response) {
                            if (response.isSuccessful()) {
                                final ArrayList<Result> results = response.body().getResults();

                                if (results.size() > 0) {
                                    final MoviesAdapter adapter = new MoviesAdapter(getContext(), results);
                                    binding.grid.setAdapter(adapter);
                                    binding.setShowLoading(true);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Search> call, Throwable t) {

                        }
                    }
            );
        }
    }

    public void initRecyclerView() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1);
        binding.grid.setLayoutManager(manager);
        binding.grid.setHasFixedSize(true);
    }


    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }
}
