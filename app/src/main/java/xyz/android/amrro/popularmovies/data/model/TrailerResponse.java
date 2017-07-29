package xyz.android.amrro.popularmovies.data.model;

import java.util.List;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 *
 * Trailer response id plus list of {@link Trailer}
 */

public final class TrailerResponse {

    private final String id;
    private final List<Trailer> results;

    public TrailerResponse(String  id, List<Trailer> results) {
        this.id = id;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrailerResponse)) return false;

        TrailerResponse that = (TrailerResponse) o;

        return (getId() != null ? getId().equals(that.getId()) : that.getId() == null) &&
                (getResults() != null ? getResults().equals(that.getResults()) : that.getResults() == null);
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getResults() != null ? getResults().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "id=" + id +
                ", results=" + results +
                '}';
    }
}
