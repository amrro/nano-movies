package xyz.android.amrro.popularmovies.data.model;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 * <p>
 * This class represents a result in /Discover api.
 */


import java.util.List;


public final class Result {
    private final Integer voteCount;
    private final Integer id;
    private final boolean video;
    private final double voteAverage;
    private final String title;
    private final double popularity;
    private final String posterPath;
    private final String originalLanguage;
    private final String originalTitle;
    private final List<Integer> genreIds;
    private final String backdropPath;
    private final boolean adult;
    private final String overview;
    private final String releaseDate;


    /**
     * @param genreIds
     * @param id
     * @param title
     * @param releaseDate
     * @param overview
     * @param posterPath
     * @param originalTitle
     * @param voteAverage
     * @param originalLanguage
     * @param adult
     * @param backdropPath
     * @param voteCount
     * @param video
     * @param popularity
     */
    public Result(Integer voteCount, Integer id, boolean video, double voteAverage, String title, double popularity, String posterPath, String originalLanguage, String originalTitle, List<Integer> genreIds, String backdropPath, boolean adult, String overview, String releaseDate) {
        super();
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Integer getVoteCount() {
        return voteCount;
    }


    public Integer getId() {
        return id;
    }


    public boolean isVideo() {
        return video;
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
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }


    public String getOriginalLanguage() {
        return originalLanguage;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }


    public List<Integer> getGenreIds() {
        return genreIds;
    }


    public String getBackdropPath() {
        return backdropPath;
    }


    public boolean isAdult() {
        return adult;
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
        if (!(o instanceof Result)) return false;

        Result result = (Result) o;

        if (isVideo() != result.isVideo()) return false;
        if (Double.compare(result.getVoteAverage(), getVoteAverage()) != 0) return false;
        if (Double.compare(result.getPopularity(), getPopularity()) != 0) return false;
        if (isAdult() != result.isAdult()) return false;
        if (getVoteCount() != null ? !getVoteCount().equals(result.getVoteCount()) : result.getVoteCount() != null)
            return false;
        if (getId() != null ? !getId().equals(result.getId()) : result.getId() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(result.getTitle()) : result.getTitle() != null)
            return false;
        if (getPosterPath() != null ? !getPosterPath().equals(result.getPosterPath()) : result.getPosterPath() != null)
            return false;
        if (getOriginalLanguage() != null ? !getOriginalLanguage().equals(result.getOriginalLanguage()) : result.getOriginalLanguage() != null)
            return false;
        if (getOriginalTitle() != null ? !getOriginalTitle().equals(result.getOriginalTitle()) : result.getOriginalTitle() != null)
            return false;
        if (getGenreIds() != null ? !getGenreIds().equals(result.getGenreIds()) : result.getGenreIds() != null)
            return false;
        if (getBackdropPath() != null ? !getBackdropPath().equals(result.getBackdropPath()) : result.getBackdropPath() != null)
            return false;
        if (getOverview() != null ? !getOverview().equals(result.getOverview()) : result.getOverview() != null)
            return false;
        return getReleaseDate() != null ? getReleaseDate().equals(result.getReleaseDate()) : result.getReleaseDate() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getVoteCount() != null ? getVoteCount().hashCode() : 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (isVideo() ? 1 : 0);
        temp = Double.doubleToLongBits(getVoteAverage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        temp = Double.doubleToLongBits(getPopularity());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getPosterPath() != null ? getPosterPath().hashCode() : 0);
        result = 31 * result + (getOriginalLanguage() != null ? getOriginalLanguage().hashCode() : 0);
        result = 31 * result + (getOriginalTitle() != null ? getOriginalTitle().hashCode() : 0);
        result = 31 * result + (getGenreIds() != null ? getGenreIds().hashCode() : 0);
        result = 31 * result + (getBackdropPath() != null ? getBackdropPath().hashCode() : 0);
        result = 31 * result + (isAdult() ? 1 : 0);
        result = 31 * result + (getOverview() != null ? getOverview().hashCode() : 0);
        result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "voteCount=" + voteCount +
                ", id=" + id +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", genreIds=" + genreIds +
                ", backdropPath='" + backdropPath + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}

