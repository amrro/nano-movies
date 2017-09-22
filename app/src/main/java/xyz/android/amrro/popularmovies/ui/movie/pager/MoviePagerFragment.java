package xyz.android.amrro.popularmovies.ui.movie.pager;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.BaseFragment;
import xyz.android.amrro.popularmovies.common.Navigator;
import xyz.android.amrro.popularmovies.databinding.PagerFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviePagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviePagerFragment extends BaseFragment {
    private PagerFragmentBinding binding;
    private MoviePagerAdapter pagerAdaper;

    public MoviePagerFragment() {
        // Required empty public constructor
    }

    public static MoviePagerFragment newInstance(@NonNull final Integer id) {
        MoviePagerFragment fragment = new MoviePagerFragment();
        Bundle args = new Bundle();
        args.putInt(Navigator.KEY_ITEM_ID, Objects.requireNonNull(id));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pagerAdaper = new MoviePagerAdapter(getFragmentManager(), itemId());
        binding.pager.setAdapter(pagerAdaper);
    }
}
