package xyz.android.amrro.popularmovies.data.model;

import android.support.annotation.NonNull;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */

public class Collection {
    @NonNull
    String id;
    @NonNull
    String name;
    String posterPath;
    String backdropPath;

    public Collection(@NonNull String id,
                      @NonNull String name,
                      String posterPath,
                      String backdropPath) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collection)) return false;

        Collection that = (Collection) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (posterPath != null ? !posterPath.equals(that.posterPath) : that.posterPath != null)
            return false;
        return backdropPath != null ? backdropPath.equals(that.backdropPath) : that.backdropPath == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        result = 31 * result + (backdropPath != null ? backdropPath.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Collection{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                '}';
    }
}
