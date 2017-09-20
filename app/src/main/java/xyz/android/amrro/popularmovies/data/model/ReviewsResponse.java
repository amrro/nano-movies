package xyz.android.amrro.popularmovies.data.model;

import java.util.List;
import java.util.Objects;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 * <p>
 * Represents {@link xyz.android.amrro.popularmovies.data.api.MoviesService#reviews(int)} Response.
 */

@SuppressWarnings("WeakerAccess")
public final class ReviewsResponse {

    private final String id;
    private final Integer page;
    private final List<Review> results;
    private final Integer totalPages;
    private final Integer totalResults;


    public ReviewsResponse(String id, Integer page, List<Review> results, Integer totalPages, Integer totalResults) {
        this.id = Objects.requireNonNull(id);
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
        if (! (o instanceof ReviewsResponse)) return false;
        ReviewsResponse response = (ReviewsResponse) o;
        return Objects.equals(getId(), response.getId()) &&
                Objects.equals(getPage(), response.getPage()) &&
                Objects.equals(getResults(), response.getResults()) &&
                Objects.equals(getTotalPages(), response.getTotalPages()) &&
                Objects.equals(getTotalResults(), response.getTotalResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPage(), getResults(), getTotalPages(), getTotalResults());
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
