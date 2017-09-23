package xyz.android.amrro.popularmovies.data.model;


import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.Objects;

public final class Trailer {

    public final String id;
    public final String key;
    public final String name;
    public final Integer size;
    public final String site;

    public Trailer(String id, String key, String name, Integer size, String site) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.size = size;
        this.site = site;
    }

    public static String buildYouTubeLink(@NonNull final String key) {
        Objects.requireNonNull(key);
        final Uri.Builder builder = Uri.parse("https://www.youtube.com/watch").buildUpon();
        builder.appendQueryParameter("v", key);
        return builder.build().toString();
    }

    public static String getYouTubeThumbnail(@NonNull final String key) {
        Objects.requireNonNull(key);
        final Uri.Builder builder = Uri.parse("https://img.youtube.com/vi").buildUpon();
        builder.appendPath(key)
                .appendPath("hqdefault.jpg");
        return builder.build().toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Trailer)) return false;
        Trailer trailer = (Trailer) o;
        return Objects.equals(id, trailer.id) &&
                Objects.equals(key, trailer.key) &&
                Objects.equals(name, trailer.name) &&
                Objects.equals(size, trailer.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, name, size);
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
