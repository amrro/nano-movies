package xyz.android.amrro.popularmovies.ui.movie.trailer;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import okhttp3.internal.Util;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.DataListAdapter;
import xyz.android.amrro.popularmovies.common.OnItemClickedListener;
import xyz.android.amrro.popularmovies.data.model.Trailer;
import xyz.android.amrro.popularmovies.databinding.CardTrailerBinding;
import xyz.android.amrro.popularmovies.utils.Utils;

public final class TrailersAdapter extends DataListAdapter<Trailer, CardTrailerBinding> {
    TrailersAdapter(OnItemClickedListener<Trailer> listener) {
        super(listener);
    }

    @Override
    protected CardTrailerBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        final CardTrailerBinding binding = DataBindingUtil.inflate(inflater, R.layout.card_trailer, parent, false);
        binding.getRoot().setOnClickListener(view -> {
            final Trailer trailer = binding.getTrailer();
            if (trailer != null) {
                Utils.openYouTube(view.getContext(), trailer.key);
                binding.share.setOnClickListener(shareView -> Utils.shareYouTube(shareView.getContext(), trailer.key));
            }
        });
        return binding;
    }

    @Override
    protected void bind(CardTrailerBinding binding, Trailer item) {
        binding.setTrailer(item);
    }
}
