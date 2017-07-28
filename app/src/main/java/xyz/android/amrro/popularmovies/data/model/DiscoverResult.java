package xyz.android.amrro.popularmovies.data.model;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 *
 *
 */


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public final class DiscoverResult {
    private final int page;
    private final int totalResults;
    private final int totalPages;
    private final List<MovieResult> results;

    /**
     * No args constructor for use in serialization
     */
    /*public DiscoverResult() {
    }*/

    /**
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
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
        if (!(o instanceof DiscoverResult)) return false;

        DiscoverResult discoverResult = (DiscoverResult) o;

        if (getPage() != discoverResult.getPage()) return false;
        if (getTotalResults() != discoverResult.getTotalResults()) return false;
        if (getTotalPages() != discoverResult.getTotalPages()) return false;
        return getResults() != null ? getResults().equals(discoverResult.getResults()) : discoverResult.getResults() == null;
    }

    @Override
    public int hashCode() {
        int result = getPage();
        result = 31 * result + getTotalResults();
        result = 31 * result + getTotalPages();
        result = 31 * result + (getResults() != null ? getResults().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DiscoverResult{" +
                "page=" + page +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                ", results=" + results +
                '}';
    }
}
