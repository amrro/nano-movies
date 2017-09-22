
package xyz.android.amrro.popularmovies.binding;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {

    @BindingAdapter("showView")
    public static void showView(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("hideView")
    public static void hideView(View view, boolean hide) {
        showView(view, ! hide);
    }
}
