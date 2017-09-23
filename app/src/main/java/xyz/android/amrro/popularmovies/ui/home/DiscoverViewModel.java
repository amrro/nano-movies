package xyz.android.amrro.popularmovies.ui.home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.model.DiscoverResult;
import xyz.android.amrro.popularmovies.data.repository.DiscoverRepository;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/27/17.
 * This class is responsible for getting list of movies based on sorting criteria.
 */

public final class DiscoverViewModel extends ViewModel {
    private final MutableLiveData<String> sort = new MutableLiveData<>();
    private final LiveData<ApiResponse<DiscoverResult>> results;

    @Inject
    DiscoverViewModel(@NonNull final DiscoverRepository repository,
                      @NonNull final Application app) {
        results = Transformations.switchMap(sort, sorting -> {
            if (sorting.equals(app.getString(R.string.sort_popularity_desc)))
                return repository.popular();
            else if (sorting.equals(app.getString(R.string.sort_vot_count_desc))) {
                return repository.topRated();
            }
            return null;
        });
    }

    void setSort(final String sorting) {
        Objects.requireNonNull(sorting);
        if (sorting.equals(this.sort.getValue())) return;
        this.sort.setValue(sorting);
    }

    String getFilter() {
        return this.sort.getValue();
    }

    LiveData<ApiResponse<DiscoverResult>> getResults() {
        return results;
    }
}
