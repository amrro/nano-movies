
package xyz.android.amrro.popularmovies.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.model.Trailer;

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

    @BindingAdapter("YouTube")
    public static void setYouTubeThumbnail(ImageView imageView, String key) {
        final String thumbnail = Trailer.getYouTubeThumbnail(key);
        Glide.with(imageView)
                .load(thumbnail)
                .into(imageView);
    }

    @BindingAdapter("logo")
    public static void setLogo(ImageView imageView, String site) {
        switch (site) {
            case "YouTube":
                imageView.setImageResource(R.drawable.ic_youtube_54dp);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_play_circle_outline_white_24dp);
        }
    }
}
