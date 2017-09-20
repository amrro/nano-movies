package xyz.android.amrro.popularmovies.utils;

import android.view.View;

import xyz.android.amrro.popularmovies.ui.home.FiltersButtonSheetFragment;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 9/20/17.
 * this interface handles the different click of fiters in {@link FiltersButtonSheetFragment}
 */

public interface FiltersHandler {
    void popular(View view);

    void favorites(View view);

    void rating(View view);
}
