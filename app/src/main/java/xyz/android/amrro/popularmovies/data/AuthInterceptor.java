package xyz.android.amrro.popularmovies.data;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/22/17.
 *
 * This interceptor is adding a query of the API key to each request.
 */

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();
        final HttpUrl originalHttpUrl = original.url();

        final HttpUrl url = originalHttpUrl
                .newBuilder()
                .addQueryParameter("api_key", "f5d2da75e7729eee412a43da5f542a9c")
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        return chain.proceed(requestBuilder.build());

    }
}
