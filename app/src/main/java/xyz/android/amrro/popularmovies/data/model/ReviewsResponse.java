package xyz.android.amrro.popularmovies.data.model;

import java.util.List;
import java.util.Objects;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 *
 * Represents {@link xyz.android.amrro.popularmovies.data.api.MoviesService#reviews(int)} Response.
 */

public final class ReviewsResponse {

    private final String id;
    private final Integer page;
    private final List<Review> results;
    private final Integer totalPages;
    private final Integer totalResults;


    public ReviewsResponse(String id, Integer page, List<Review> results, Integer totalPages, Integer totalResults) {
        Objects.requireNonNull(id);

        this.id = id;
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public String getId() {
        return id;
    }

    public Integer getPage() {
        return page;
    }

    public List<Review> getResults() {
        return results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewsResponse)) return false;

        ReviewsResponse that = (ReviewsResponse) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getPage() != null ? !getPage().equals(that.getPage()) : that.getPage() != null)
            return false;
        if (getResults() != null ? !getResults().equals(that.getResults()) : that.getResults() != null)
            return false;
        if (getTotalPages() != null ? !getTotalPages().equals(that.getTotalPages()) : that.getTotalPages() != null)
            return false;
        return getTotalResults() != null ? getTotalResults().equals(that.getTotalResults()) : that.getTotalResults() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPage() != null ? getPage().hashCode() : 0);
        result = 31 * result + (getResults() != null ? getResults().hashCode() : 0);
        result = 31 * result + (getTotalPages() != null ? getTotalPages().hashCode() : 0);
        result = 31 * result + (getTotalResults() != null ? getTotalResults().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReviewsResponse{" +
                "id='" + id + '\'' +
                ", page=" + page +
                ", results=" + results +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
