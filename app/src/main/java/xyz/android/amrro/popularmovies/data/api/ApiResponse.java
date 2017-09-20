package xyz.android.amrro.popularmovies.data.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/29/17.
 *
 * General Class for API responses. Either carry the result of the error, CANNOT be both.
 */

public final class ApiResponse<V> {

    @Nullable
    private final V data;

    @Nullable
    private final Throwable error;

    /**
     * In Case of successful API request, pass the received data here.
     *
     * @param Data received data from successful API Call.
     */
    public ApiResponse(@NonNull final V Data) {
        this.data = Objects.requireNonNull(Data);
        this.error = null;
    }

    /**
     * In case of failed API request, pass error.
     * @param error The cause of the failed API Call.
     */
    public ApiResponse(@NonNull final Throwable error) {
        this.error = Objects.requireNonNull(error);
        this.data = null;
    }

    public boolean isSuccessful() {
        return data != null && error == null;
    }

    @NonNull
    public V getData() {
        if (data == null) {
            throw new IllegalStateException("Data is null; Call ApiResponse#isSuccessful() first.");
        }
        return data;
    }

    @NonNull
    public Throwable getError() {
        if (error == null) {
            throw new IllegalStateException("error is null; Call ApiResponse#isSuccessful() first.");
        }
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiResponse)) return false;

        ApiResponse<?> that = (ApiResponse<?>) o;

        return (data != null ? getData().equals(that.getData()) : that.data == null) &&
                (error != null ? getError().equals(that.getError()) : that.error == null);
    }

    @Override
    public int hashCode() {
        int result = data != null ? getData().hashCode() : 0;
        result = 31 * result + (error != null ? getError().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "Data=" + data +
                ", error=" + error +
                '}';
    }
}
