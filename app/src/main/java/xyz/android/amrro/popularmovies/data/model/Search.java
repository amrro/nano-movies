package xyz.android.amrro.popularmovies.data.model;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 */


import java.util.List;


public final class Search {
    private final int page;
    private final int totalResults;
    private final int totalPages;
    private final List<Result> results;

    /**
     * No args constructor for use in serialization
     */
    /*public Search() {
    }*/

    /**
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public Search(int page, int totalResults, int totalPages, List<Result> results) {
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


    public List<Result> getResults() {
        return results;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Search)) return false;

        Search search = (Search) o;

        if (getPage() != search.getPage()) return false;
        if (getTotalResults() != search.getTotalResults()) return false;
        if (getTotalPages() != search.getTotalPages()) return false;
        return getResults() != null ? getResults().equals(search.getResults()) : search.getResults() == null;
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
        return "Search{" +
                "page=" + page +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                ", results=" + results +
                '}';
    }
}
