package xyz.android.amrro.popularmovies.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = Movie.TABLE_NAME)
public final class Movie {


    public static final String TABLE_NAME = "favorite_movies";

    @PrimaryKey
    private /*final*/ Integer id;
    private /*final*/ Boolean adult;
    private /*final*/ String backdropPath;
    @Ignore
    private /*final*/ Collection belongsToCollection;
    @Ignore
    private /*final*/ Integer budget;
    @Ignore
    private /*final*/ List<Genre> genres;
    @Ignore
    private /*final*/ String homepage;
    private /*final*/ String imdbId;
    @Ignore
    private /*final*/ String originalLanguage;
    private /*final*/ String originalTitle;
    private /*final*/ String overview;
    private /*final*/ Double popularity;
    private /*final*/ String posterPath;
    private /*final*/ String releaseDate;
    private /*final*/ Integer revenue;
    private /*final*/ Integer runtime;
    @Ignore
    private /*final*/ String status;
    @Ignore
    private /*final*/ String tagline;
    private /*final*/ String title;
    private /*final*/ Boolean video;
    private /*final*/ Double voteAverage;
    private /*final*/ Integer voteCount;


    public Movie() {
    }

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
        if (backdropPath == null)
            return null;

        if (backdropPath.startsWith("https://"))
            return backdropPath;

        return String.format("https://image.tmdb.org/t/p/w500%s", backdropPath);
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
        if (posterPath == null)
            return null;

        if (posterPath.startsWith("https://"))
            return posterPath;

        return "https://image.tmdb.org/t/p/w500" + posterPath;
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


    public void setId(Integer id) {
        this.id = id;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setBelongsToCollection(Collection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(Boolean video) {
        this.video = video;
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

    public static class Builder {
        private Boolean adult;
        private String backdropPath;
        private Collection belongsToCollection;
        private Integer budget;
        private List<Genre> genres;
        private String homepage;
        private Integer id;
        private String imdbId;
        private String originalLanguage;
        private String originalTitle;
        private String overview;
        private Double popularity;
        private String posterPath;
        private String releaseDate;
        private Integer revenue;
        private Integer runtime;
        private String status;
        private String tagline;
        private String title;
        private Boolean video;
        private Double voteAverage;
        private Integer voteCount;

        public Builder() {
        }

        public Builder setAdult(Boolean adult) {
            this.adult = adult;
            return this;
        }

        public Builder setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
            return this;
        }

        public Builder setBelongsToCollection(Collection belongsToCollection) {
            this.belongsToCollection = belongsToCollection;
            return this;
        }

        public Builder setBudget(Integer budget) {
            this.budget = budget;
            return this;
        }

        public Builder setGenres(List<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public Builder setHomepage(String homepage) {
            this.homepage = homepage;
            return this;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setImdbId(String imdbId) {
            this.imdbId = imdbId;
            return this;
        }

        public Builder setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
            return this;
        }

        public Builder setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public Builder setOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public Builder setPopularity(Double popularity) {
            this.popularity = popularity;
            return this;
        }

        public Builder setPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public Builder setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder setRevenue(Integer revenue) {
            this.revenue = revenue;
            return this;
        }

        public Builder setRuntime(Integer runtime) {
            this.runtime = runtime;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setTagline(String tagline) {
            this.tagline = tagline;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setVideo(Boolean video) {
            this.video = video;
            return this;
        }

        public Builder setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
            return this;
        }

        public Builder setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
            return this;
        }

        public Movie build() {
            return new Movie(adult, backdropPath, belongsToCollection, budget, genres, homepage, id, imdbId, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, revenue, runtime, status, tagline, title, video, voteAverage, voteCount);
        }
    }
}