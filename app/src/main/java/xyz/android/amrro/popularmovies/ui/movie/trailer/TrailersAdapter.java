package xyz.android.amrro.popularmovies.ui.movie.trailer;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.DataListAdapter;
import xyz.android.amrro.popularmovies.common.OnItemClickedListener;
import xyz.android.amrro.popularmovies.data.model.Trailer;
import xyz.android.amrro.popularmovies.databinding.CardTrailerBinding;

public final class TrailersAdapter extends DataListAdapter<Trailer, CardTrailerBinding> {
    public TrailersAdapter(OnItemClickedListener<Trailer> listener) {
        super(listener);
    }

    @Override
    protected CardTrailerBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        final CardTrailerBinding binding = DataBindingUtil.inflate(inflater, R.layout.card_trailer, parent, false);
        return binding;
    }

    @Override
    protected void bind(CardTrailerBinding binding, Trailer item) {
        binding.setTrailer(item);
    }
}
