package xyz.android.amrro.popularmovies.data.model;

import java.util.Objects;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 * <p>
 * This class represents a result in /Discover api.
 */


public final class MovieResult {
    private final Integer id;
    private final String title;
    private final String overview;
    private final String releaseDate;
    private final String posterPath;
    private final String backdropPath;
    private final Integer voteCount;
    private final double voteAverage;
    private final double popularity;


    public MovieResult(Integer id,
                       String title,
                       String overview,
                       String releaseDate,
                       String posterPath,
                       String backdropPath,
                       Integer voteCount,
                       double voteAverage,
                       double popularity) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }


    public Integer getId() {
        return id;
    }


    public double getVoteAverage() {
        return voteAverage;
    }


    public String getTitle() {
        return title;
    }


    public double getPopularity() {
        return popularity;
    }


    public String getPosterPath() {
        if (posterPath != null)
            return "https://image.tmdb.org/t/p/w500" + posterPath;
        return null;
    }


    public String getBackdropPath() {
        if (backdropPath != null)
            return String.format("https://image.tmdb.org/t/p/w500%s", backdropPath);

        return null;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieResult)) return false;
        MovieResult that = (MovieResult) o;
        return Double.compare(that.getVoteAverage(), getVoteAverage()) == 0 &&
                Double.compare(that.getPopularity(), getPopularity()) == 0 &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getOverview(), that.getOverview()) &&
                Objects.equals(getReleaseDate(), that.getReleaseDate()) &&
                Objects.equals(getPosterPath(), that.getPosterPath()) &&
                Objects.equals(getBackdropPath(), that.getBackdropPath()) &&
                Objects.equals(getVoteCount(), that.getVoteCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getTitle(),
                getOverview(),
                getReleaseDate(),
                getPosterPath(),
                getBackdropPath(),
                getVoteCount(),
                getVoteAverage(),
                getPopularity());
    }


    @Override
    public String toString() {
        return "MovieResult{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", voteCount=" + voteCount +
                ", voteAverage=" + voteAverage +
                ", popularity=" + popularity +
                '}';
    }
}

