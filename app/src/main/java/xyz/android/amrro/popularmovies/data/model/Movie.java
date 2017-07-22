package xyz.android.amrro.popularmovies.data.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */

public class Movie {

    @NonNull
    public Integer id;
    @NonNull
    public Boolean adult;
    public String backdropPath;
    public Collection BelongsToCollection;
    public Integer Budget;
    public List<Genre> genres;
    public String homepage;
    public String originalTitle;
    public String overview;
    public Double popularity;
    public String posterPath;
    public String releaseDate;
    public String revenue;
    public String runtime;
    public String status;
    public String tagline;
    public String title;
    public Boolean video;
    public Double voteAverage;
    public Integer voteCount;

    public Movie(@NonNull Integer id,
                 @NonNull Boolean adult,
                 String backdropPath,
                 Collection belongsToCollection,
                 Integer budget,
                 List<Genre> genres,
                 String homepage,
                 String originalTitle,
                 String overview,
                 Double popularity,
                 String posterPath,
                 String releaseDate,
                 String revenue,
                 String runtime,
                 String status,
                 String tagline,
                 String title,
                 Boolean video,
                 Double voteAverage,
                 Integer voteCount) {
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        BelongsToCollection = belongsToCollection;
        Budget = budget;
        this.genres = genres;
        this.homepage = homepage;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (!id.equals(movie.id)) return false;
        if (!adult.equals(movie.adult)) return false;
        if (backdropPath != null ? !backdropPath.equals(movie.backdropPath) : movie.backdropPath != null)
            return false;
        if (BelongsToCollection != null ? !BelongsToCollection.equals(movie.BelongsToCollection) : movie.BelongsToCollection != null)
            return false;
        if (Budget != null ? !Budget.equals(movie.Budget) : movie.Budget != null) return false;
        if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;
        if (homepage != null ? !homepage.equals(movie.homepage) : movie.homepage != null)
            return false;
        if (originalTitle != null ? !originalTitle.equals(movie.originalTitle) : movie.originalTitle != null)
            return false;
        if (overview != null ? !overview.equals(movie.overview) : movie.overview != null)
            return false;
        if (popularity != null ? !popularity.equals(movie.popularity) : movie.popularity != null)
            return false;
        if (posterPath != null ? !posterPath.equals(movie.posterPath) : movie.posterPath != null)
            return false;
        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null)
            return false;
        if (revenue != null ? !revenue.equals(movie.revenue) : movie.revenue != null) return false;
        if (runtime != null ? !runtime.equals(movie.runtime) : movie.runtime != null) return false;
        if (status != null ? !status.equals(movie.status) : movie.status != null) return false;
        if (tagline != null ? !tagline.equals(movie.tagline) : movie.tagline != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (video != null ? !video.equals(movie.video) : movie.video != null) return false;
        if (voteAverage != null ? !voteAverage.equals(movie.voteAverage) : movie.voteAverage != null)
            return false;
        return voteCount != null ? voteCount.equals(movie.voteCount) : movie.voteCount == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + adult.hashCode();
        result = 31 * result + (backdropPath != null ? backdropPath.hashCode() : 0);
        result = 31 * result + (BelongsToCollection != null ? BelongsToCollection.hashCode() : 0);
        result = 31 * result + (Budget != null ? Budget.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (popularity != null ? popularity.hashCode() : 0);
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (revenue != null ? revenue.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tagline != null ? tagline.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (voteAverage != null ? voteAverage.hashCode() : 0);
        result = 31 * result + (voteCount != null ? voteCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", adult=" + adult +
                ", backdropPath='" + backdropPath + '\'' +
                ", BelongsToCollection=" + BelongsToCollection +
                ", Budget=" + Budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", revenue='" + revenue + '\'' +
                ", runtime='" + runtime + '\'' +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
