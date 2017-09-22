package xyz.android.amrro.popularmovies.data.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("WeakerAccess")
public final class DiscoverResult {
    private final int page;
    private final List<MovieResult> results;

    public DiscoverResult(int page, int totalResults, int totalPages, List<MovieResult> results) {
        super();
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    @NonNull
    public ArrayList<MovieResult> getResults() {
        if (results == null) {
            return new ArrayList<>();
        }
        return (ArrayList<MovieResult>) results;
    }

}
