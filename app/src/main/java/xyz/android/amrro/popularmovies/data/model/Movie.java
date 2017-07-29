package xyz.android.amrro.popularmovies.data.model;

import java.util.List;

public final class Movie {
    private final Boolean adult;
    private final String backdropPath;
    private final Collection belongsToCollection;
    private final Integer budget;
    private final List<Genre> genres;
    private final String homepage;
    private final Integer id;
    private final String imdbId;
    private final String originalLanguage;
    private final String originalTitle;
    private final String overview;
    private final Double popularity;
    private final String posterPath;
    private final String releaseDate;
    private final Integer revenue;
    private final Integer runtime;
    private final String status;
    private final String tagline;
    private final String title;
    private final Boolean video;
    private final Double voteAverage;
    private final Integer voteCount;


    public Movie(Boolean adult,
                 String backdropPath,
                 Collection belongsToCollection,
                 Integer budget,
                 List<Genre> genres,
                 String homepage,
                 Integer id,
                 String imdbId,
                 String originalLanguage,
                 String originalTitle,
                 String overview,
                 Double popularity,
                 String posterPath,
                 String releaseDate,
                 Integer revenue,
                 Integer runtime,
                 String status,
                 String tagline,
                 String title,
                 Boolean video,
                 Double voteAverage,
                 Integer voteCount) {
        super();

        this.adult = adult;
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }


    public Boolean getAdult() {
        return adult;
    }


    public String getBackdropPath() {
        if (backdropPath != null)
            return String.format("https://image.tmdb.org/t/p/w500%s", backdropPath);

        return null;
    }

    public Collection getBelongsToCollection() {
        return belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public Integer getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        if (posterPath != null)
            return "https://image.tmdb.org/t/p/w500" + posterPath;
        return null;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }


    public String getStatus() {
        return status;
    }


    public String getTagline() {
        return tagline;
    }


    public String getTitle() {
        return title;
    }


    public Boolean getVideo() {
        return video;
    }


    public Double getVoteAverage() {
        return voteAverage;
    }


    public Integer getVoteCount() {
        return voteCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getAdult() != null ? !getAdult().equals(movie.getAdult()) : movie.getAdult() != null)
            return false;
        if (getBackdropPath() != null ? !getBackdropPath().equals(movie.getBackdropPath()) : movie.getBackdropPath() != null)
            return false;
        if (getBelongsToCollection() != null ? !getBelongsToCollection().equals(movie.getBelongsToCollection()) : movie.getBelongsToCollection() != null)
            return false;
        if (getBudget() != null ? !getBudget().equals(movie.getBudget()) : movie.getBudget() != null)
            return false;
        if (getGenres() != null ? !getGenres().equals(movie.getGenres()) : movie.getGenres() != null)
            return false;
        if (getHomepage() != null ? !getHomepage().equals(movie.getHomepage()) : movie.getHomepage() != null)
            return false;
        if (getId() != null ? !getId().equals(movie.getId()) : movie.getId() != null) return false;
        if (getImdbId() != null ? !getImdbId().equals(movie.getImdbId()) : movie.getImdbId() != null)
            return false;
        if (getOriginalLanguage() != null ? !getOriginalLanguage().equals(movie.getOriginalLanguage()) : movie.getOriginalLanguage() != null)
            return false;
        if (getOriginalTitle() != null ? !getOriginalTitle().equals(movie.getOriginalTitle()) : movie.getOriginalTitle() != null)
            return false;
        if (getOverview() != null ? !getOverview().equals(movie.getOverview()) : movie.getOverview() != null)
            return false;
        if (getPopularity() != null ? !getPopularity().equals(movie.getPopularity()) : movie.getPopularity() != null)
            return false;
        if (getPosterPath() != null ? !getPosterPath().equals(movie.getPosterPath()) : movie.getPosterPath() != null)
            return false;
        if (getReleaseDate() != null ? !getReleaseDate().equals(movie.getReleaseDate()) : movie.getReleaseDate() != null)
            return false;
        if (getRevenue() != null ? !getRevenue().equals(movie.getRevenue()) : movie.getRevenue() != null)
            return false;
        if (getRuntime() != null ? !getRuntime().equals(movie.getRuntime()) : movie.getRuntime() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(movie.getStatus()) : movie.getStatus() != null)
            return false;
        if (getTagline() != null ? !getTagline().equals(movie.getTagline()) : movie.getTagline() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(movie.getTitle()) : movie.getTitle() != null)
            return false;
        if (getVideo() != null ? !getVideo().equals(movie.getVideo()) : movie.getVideo() != null)
            return false;
        if (getVoteAverage() != null ? !getVoteAverage().equals(movie.getVoteAverage()) : movie.getVoteAverage() != null)
            return false;
        return getVoteCount() != null ? getVoteCount().equals(movie.getVoteCount()) : movie.getVoteCount() == null;
    }

    @Override
    public int hashCode() {
        int result = getAdult() != null ? getAdult().hashCode() : 0;
        result = 31 * result + (getBackdropPath() != null ? getBackdropPath().hashCode() : 0);
        result = 31 * result + (getBelongsToCollection() != null ? getBelongsToCollection().hashCode() : 0);
        result = 31 * result + (getBudget() != null ? getBudget().hashCode() : 0);
        result = 31 * result + (getGenres() != null ? getGenres().hashCode() : 0);
        result = 31 * result + (getHomepage() != null ? getHomepage().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getImdbId() != null ? getImdbId().hashCode() : 0);
        result = 31 * result + (getOriginalLanguage() != null ? getOriginalLanguage().hashCode() : 0);
        result = 31 * result + (getOriginalTitle() != null ? getOriginalTitle().hashCode() : 0);
        result = 31 * result + (getOverview() != null ? getOverview().hashCode() : 0);
        result = 31 * result + (getPopularity() != null ? getPopularity().hashCode() : 0);
        result = 31 * result + (getPosterPath() != null ? getPosterPath().hashCode() : 0);
        result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
        result = 31 * result + (getRevenue() != null ? getRevenue().hashCode() : 0);
        result = 31 * result + (getRuntime() != null ? getRuntime().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getTagline() != null ? getTagline().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getVideo() != null ? getVideo().hashCode() : 0);
        result = 31 * result + (getVoteAverage() != null ? getVoteAverage().hashCode() : 0);
        result = 31 * result + (getVoteCount() != null ? getVoteCount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "adult=" + adult +
                ", backdropPath='" + backdropPath + '\'' +
                ", belongsToCollection=" + belongsToCollection +
                ", budget=" + budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", imdbId='" + imdbId + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}