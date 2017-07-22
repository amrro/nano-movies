package xyz.android.amrro.popularmovies.data.model;

import android.support.annotation.NonNull;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 */

public class Genre {
    @NonNull
    public String id;

    @NonNull
    public String name;

    public Genre(@NonNull String id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;

        Genre genre = (Genre) o;

        if (!id.equals(genre.id)) return false;
        return name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
