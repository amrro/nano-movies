package xyz.android.amrro.popularmovies.data.model;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 * <p>
 * This class represents a result in /Discover api.
 */


import java.util.List;


public final class MovieResult {
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


    public MovieResult(Integer voteCount,
                       Integer id,
                       boolean video,
                       double voteAverage,
                       String title,
                       double popularity,
                       String posterPath,
                       String originalLanguage,
                       String originalTitle,
                       List<Integer> genreIds,
                       String backdropPath,
                       boolean adult,
                       String overview,
                       String releaseDate) {
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
        if (posterPath != null)
            return "https://image.tmdb.org/t/p/w500" + posterPath;
        return null;
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
        if (backdropPath != null)
            return String.format("https://image.tmdb.org/t/p/w500%s", backdropPath);

        return null;
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
        if (!(o instanceof MovieResult)) return false;

        MovieResult movieResult = (MovieResult) o;

        if (isVideo() != movieResult.isVideo()) return false;
        if (Double.compare(movieResult.getVoteAverage(), getVoteAverage()) != 0) return false;
        if (Double.compare(movieResult.getPopularity(), getPopularity()) != 0) return false;
        if (isAdult() != movieResult.isAdult()) return false;
        if (getVoteCount() != null ? !getVoteCount().equals(movieResult.getVoteCount()) : movieResult.getVoteCount() != null)
            return false;
        if (getId() != null ? !getId().equals(movieResult.getId()) : movieResult.getId() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(movieResult.getTitle()) : movieResult.getTitle() != null)
            return false;
        if (getPosterPath() != null ? !getPosterPath().equals(movieResult.getPosterPath()) : movieResult.getPosterPath() != null)
            return false;
        if (getOriginalLanguage() != null ? !getOriginalLanguage().equals(movieResult.getOriginalLanguage()) : movieResult.getOriginalLanguage() != null)
            return false;
        if (getOriginalTitle() != null ? !getOriginalTitle().equals(movieResult.getOriginalTitle()) : movieResult.getOriginalTitle() != null)
            return false;
        if (getGenreIds() != null ? !getGenreIds().equals(movieResult.getGenreIds()) : movieResult.getGenreIds() != null)
            return false;
        if (getBackdropPath() != null ? !getBackdropPath().equals(movieResult.getBackdropPath()) : movieResult.getBackdropPath() != null)
            return false;
        if (getOverview() != null ? !getOverview().equals(movieResult.getOverview()) : movieResult.getOverview() != null)
            return false;
        return getReleaseDate() != null ? getReleaseDate().equals(movieResult.getReleaseDate()) : movieResult.getReleaseDate() == null;
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
        return "MovieResult{" +
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

