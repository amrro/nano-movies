package xyz.android.amrro.popularmovies.data.model;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 */


import java.util.ArrayList;
import java.util.List;


public class Search {
    private int page;
    private int totalResults;
    private int totalPages;
    private List<Result> results = new ArrayList<Result>();

    /**
     * No args constructor for use in serialization
     */
    public Search() {
    }

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

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
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
