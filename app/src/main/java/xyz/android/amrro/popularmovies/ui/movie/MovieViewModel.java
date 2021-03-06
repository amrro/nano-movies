package xyz.android.amrro.popularmovies.ui.movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.Objects;

import javax.inject.Inject;

import xyz.android.amrro.popularmovies.data.api.ApiResponse;
import xyz.android.amrro.popularmovies.data.model.Movie;
import xyz.android.amrro.popularmovies.data.model.ReviewsResponse;
import xyz.android.amrro.popularmovies.data.model.TrailerResponse;
import xyz.android.amrro.popularmovies.data.repository.MovieRepository;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 * <p>
 * ViewModel for movie {@link MovieDetailsFragment}
 */

public final class MovieViewModel extends ViewModel {

    private final MutableLiveData<Integer> movieId = new MutableLiveData<>();
    private final LiveData<ApiResponse<Movie>> movie;
    private final LiveData<ApiResponse<ReviewsResponse>> reviews;
    private final LiveData<ApiResponse<TrailerResponse>> trailers;
    private final LiveData<Boolean> isFavorite;
    @NonNull
    private final MovieRepository repository;

    @Inject
    MovieViewModel(@NonNull final MovieRepository repository) {
        movie = Transformations.switchMap(movieId, repository::movie);
        reviews = Transformations.switchMap(movieId, repository::reviews);
        trailers = Transformations.switchMap(movieId, repository::trailers);
        isFavorite = Transformations.switchMap(movieId, repository::query);
        this.repository = repository;
    }


    public MovieViewModel setMovieId(@NonNull final Integer id) {
        if (Objects.requireNonNull(id).equals(this.movieId.getValue())) {
            return this;
        }
        this.movieId.setValue(id);
        return this;
    }


    public LiveData<ApiResponse<Movie>> getMovie() {
        return movie;
    }

    public LiveData<ApiResponse<ReviewsResponse>> reviews() {
        return reviews;
    }

    public LiveData<ApiResponse<TrailerResponse>> trailers() {
        return trailers;
    }

    @NonNull
    LiveData<Boolean> isFavorite() {
        return isFavorite;
    }

    LiveData<Boolean> un_favorite(@NonNull final Movie movie) {
        return repository.toggleFavorite(Objects.requireNonNull(movie));
    }

    void retry() {
        if (this.movieId.getValue() != null) {
            this.movieId.setValue(this.movieId.getValue());
        }
    }
}
