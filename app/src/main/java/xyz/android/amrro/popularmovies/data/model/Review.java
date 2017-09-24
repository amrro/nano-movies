package xyz.android.amrro.popularmovies.data.model;

import java.util.Objects;

public final class Review {
    public final String id;
    public final String author;
    public final String content;
    public final String url;


    public Review(String id,
                  String author,
                  String content,
                  String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(author, review.author) &&
                Objects.equals(content, review.content) &&
                Objects.equals(url, review.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content, url);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
