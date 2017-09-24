package xyz.android.amrro.popularmovies.data.model;

import java.util.Objects;

public final class Movie {


    public static final String TABLE_NAME = "favorite_movies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_RELEASE = "releaseDate";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_POSTER = "posterPath";
    public static final String COLUMN_BACKDROP = "backdropPath";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_VOTE_AVERAGE = "voteAverage";
    public static final String COLUMN_VOTE_COUNT = "voteCount";

    public static final String BASE_URL = "https://image.tmdb.org/t/p";

    private /*final*/ Integer id;
    private /*final*/ String title;
    private /*final*/ String overview;
    private /*final*/ String backdropPath;
    private /*final*/ String posterPath;
    private /*final*/ String releaseDate;
    private /*final*/ Double popularity;
    private /*final*/ Double voteAverage;
    private /*final*/ Integer voteCount;

    public Movie(Integer id,
                 String title,
                 String overview,
                 String backdropPath,
                 String posterPath,
                 String releaseDate,
                 Double popularity,
                 Double voteAverage,
                 Integer voteCount) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.title = title;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getPopularity() {
        return popularity;
    }


    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        return Objects.equals(getId(), movie.getId()) &&
                Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getOverview(), movie.getOverview()) &&
                Objects.equals(getBackdropPath(), movie.getBackdropPath()) &&
                Objects.equals(getPosterPath(), movie.getPosterPath()) &&
                Objects.equals(getReleaseDate(), movie.getReleaseDate()) &&
                Objects.equals(getPopularity(), movie.getPopularity()) &&
                Objects.equals(getVoteAverage(), movie.getVoteAverage()) &&
                Objects.equals(getVoteCount(), movie.getVoteCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getTitle(),
                getOverview(),
                getBackdropPath(),
                getPosterPath(),
                getReleaseDate(),
                getPopularity(),
                getVoteAverage(),
                getVoteCount());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", popularity=" + popularity +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}