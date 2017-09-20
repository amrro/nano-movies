package xyz.android.amrro.popularmovies.data;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import xyz.android.amrro.popularmovies.R;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 * This interceptor is adding a query of the API key to each request.
 */

public final class AuthInterceptor implements Interceptor {

    private final Context context;

    public AuthInterceptor(Application application) {
        this.context = application.getApplicationContext();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request original = chain.request();
        final HttpUrl originalHttpUrl = original.url();

        final HttpUrl url = originalHttpUrl
                .newBuilder()
                .addQueryParameter("api_key", context.getString(R.string.movies_api_key))
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        return chain.proceed(requestBuilder.build());

    }
}
