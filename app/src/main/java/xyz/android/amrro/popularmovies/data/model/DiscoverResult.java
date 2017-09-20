package xyz.android.amrro.popularmovies.data.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class DiscoverResult {
    private final int page;
    private final int totalResults;
    private final int totalPages;
    private final List<MovieResult> results;

    public DiscoverResult(int page, int totalResults, int totalPages, List<MovieResult> results) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public int getTotalPages() {
        return totalPages;
    }


    @NonNull
    public ArrayList<MovieResult> getResults() {
        if (results == null) {
            return new ArrayList<>();
        }

        return (ArrayList<MovieResult>) results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof DiscoverResult)) return false;
        DiscoverResult that = (DiscoverResult) o;
        return getPage() == that.getPage() &&
                getTotalResults() == that.getTotalResults() &&
                getTotalPages() == that.getTotalPages() &&
                Objects.equals(getResults(), that.getResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage(), getTotalResults(), getTotalPages(), getResults());
    }
}
