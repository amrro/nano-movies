package xyz.android.amrro.popularmovies.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

import xyz.android.amrro.popularmovies.ui.home.HomeActivity;
import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsActivity;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 9/20/17.
 * This class is responsible for moving user between the different
 * activities of the application.
 */

public final class Navigator {
    static String KEY_ITEM_ID = "KEY_ITEM_ID";
    private final Context context;

    Navigator(Context context) {
        this.context = Objects.requireNonNull(context);
    }

    public void toHome() {
        navigateTo(HomeActivity.class, null);
    }

    public void toDetails(@NonNull final Integer id) {
        navigateTo(MovieDetailsActivity.class, Objects.requireNonNull(id));
    }

    private void navigateTo(@NonNull final Class<? extends BaseActivity> cls, @Nullable final Integer id) {
        final Intent intent = new Intent(context, cls);
        if (id != null) intent.putExtra(KEY_ITEM_ID, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
