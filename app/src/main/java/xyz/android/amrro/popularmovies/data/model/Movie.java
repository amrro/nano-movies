package xyz.android.amrro.popularmovies.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = Movie.TABLE_NAME)
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

    @PrimaryKey
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


    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getId() != null ? !getId().equals(movie.getId()) : movie.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(movie.getTitle()) : movie.getTitle() != null)
            return false;
        if (getOverview() != null ? !getOverview().equals(movie.getOverview()) : movie.getOverview() != null)
            return false;
        if (getBackdropPath() != null ? !getBackdropPath().equals(movie.getBackdropPath()) : movie.getBackdropPath() != null)
            return false;
        if (getPosterPath() != null ? !getPosterPath().equals(movie.getPosterPath()) : movie.getPosterPath() != null)
            return false;
        if (getReleaseDate() != null ? !getReleaseDate().equals(movie.getReleaseDate()) : movie.getReleaseDate() != null)
            return false;
        if (getPopularity() != null ? !getPopularity().equals(movie.getPopularity()) : movie.getPopularity() != null)
            return false;
        if (getVoteAverage() != null ? !getVoteAverage().equals(movie.getVoteAverage()) : movie.getVoteAverage() != null)
            return false;
        return getVoteCount() != null ? getVoteCount().equals(movie.getVoteCount()) : movie.getVoteCount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getOverview() != null ? getOverview().hashCode() : 0);
        result = 31 * result + (getBackdropPath() != null ? getBackdropPath().hashCode() : 0);
        result = 31 * result + (getPosterPath() != null ? getPosterPath().hashCode() : 0);
        result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
        result = 31 * result + (getPopularity() != null ? getPopularity().hashCode() : 0);
        result = 31 * result + (getVoteAverage() != null ? getVoteAverage().hashCode() : 0);
        result = 31 * result + (getVoteCount() != null ? getVoteCount().hashCode() : 0);
        return result;
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