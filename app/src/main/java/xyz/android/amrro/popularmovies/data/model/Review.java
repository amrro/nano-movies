package xyz.android.amrro.popularmovies.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public final class Review {

    @PrimaryKey
    private final String id;
    private final String author;
    private final String content;
    private final String url;


    public Review(String id,
                  String author,
                  String content,
                  String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;

        Review review = (Review) o;

        return (getId() != null ? getId().equals(review.getId()) : review.getId() == null) &&
                (getAuthor() != null ? getAuthor().equals(review.getAuthor()) : review.getAuthor() == null) &&
                (getContent() != null ? getContent().equals(review.getContent()) : review.getContent() == null) &&
                (url != null ? url.equals(review.url) : review.url == null);
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
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
