package xyz.android.amrro.popularmovies.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Objects;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.BaseActivity;
import xyz.android.amrro.popularmovies.common.SlideUpItemAnimator;
import xyz.android.amrro.popularmovies.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding binding;
    private DiscoverViewModel discover;
    private MoviesAdapter adapter;
    private String filter;

    private static final int LOADER_MOVIES = 684;
    private static final String KEY_FILTER_ROTATE = "KEY_FILTER_ROTATE";
    private static final String KEY_RECYCLER_POSITION = "KEY_RECYCLER_POSITION";
    private Parcelable layoutManagerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(binding.toolbar);
        animateToolbar(getString(R.string.sort_popularity_desc));

        initRecyclerView();
        buildViewModel();
    }


    @Override
    protected void onSaveInstanceState(Bundle out) {
        out.putString(KEY_FILTER_ROTATE, filter);
        out.putParcelable(KEY_RECYCLER_POSITION, binding.grid.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(out);
    }

    @Override
    protected void onRestoreInstanceState(Bundle in) {
        super.onRestoreInstanceState(in);
        if (in != null) {
            setFilter(in.getString(KEY_FILTER_ROTATE, getString(R.string.sort_popularity_desc)));
            layoutManagerState = in.getParcelable(KEY_RECYCLER_POSITION);
            binding.grid.getLayoutManager().onRestoreInstanceState(layoutManagerState);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                final FiltersButtonSheetFragment sheetFragment = new FiltersButtonSheetFragment();
                sheetFragment.setListener(this::setFilter);
                sheetFragment.show(getSupportFragmentManager(), sheetFragment.getTag());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecyclerView() {
        adapter = new MoviesAdapter(movie -> snack(movie.getTitle()));
        binding.grid.setAdapter(adapter);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.span_count));
        binding.grid.setLayoutManager(layoutManager);
    }

    private void buildViewModel() {
        discover = getViewModel(DiscoverViewModel.class);
        discover.getResults().observe(this, response -> {
            if (response != null) {
                if (response.isSuccessful()) {
                    adapter.replace(response.getData().getResults());
                } else {
                    toast(response.getError().getMessage());
                }
            }
        });

        if (filter == null) {
            filter = getString(R.string.sort_popularity_desc);
            discover.setSort(filter);
        }
    }

    private void setFilter(String newFilter) {
        if (! Objects.equals(this.filter, newFilter)) {
            this.filter = newFilter;
            if (filter.equals(getString(R.string.sort_favorites))) {
//                loaderManager.initLoader(LOADER_MOVIES, null, loaderCallbacks);
            } else {
                discover.setSort(filter);
            }
        }
    }

    void animateToolbar(String title) {
        if (binding != null) {
            View view = binding.toolbar.getChildAt(0);
            if (view != null && view instanceof TextView) {
                TextView titleTv = (TextView) view;
                titleTv.setAlpha(0f);
                titleTv.setScaleX(0.8f);
                titleTv.setText(title);
                titleTv.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .setStartDelay(300)
                        .setDuration(1000)
                        .setInterpolator(AnimationUtils.loadInterpolator(
                                this,
                                android.R.interpolator.fast_out_slow_in));
            }
        }
    }


}
