package xyz.android.amrro.popularmovies.ui.movie.pager;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
public final class MoviePagerFragment extends BaseFragment {
    static final String CURRENT_PAGE = "CURRENT_PAGE";

    private PagerFragmentBinding binding;
    private int currentItem;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // restore current tab.
        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_PAGE)) {
            currentItem = savedInstanceState.getInt(CURRENT_PAGE, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MoviePagerAdapter pagerAdapter = new MoviePagerAdapter(getFragmentManager(), itemId());
        binding.pager.setAdapter(pagerAdapter);
        setUpTabs();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_PAGE, currentItem);
        super.onSaveInstanceState(outState);
    }

    private void setUpTabs() {
        if (currentItem >= 0 && currentItem < 3) {
            binding.pager.setCurrentItem(currentItem);
        } else {
            currentItem = 0;
        }

        binding.navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_info:
                    currentItem = 0;
                    binding.pager.setCurrentItem(currentItem);
                    break;

                case R.id.action_trailers:
                    currentItem = 1;
                    binding.pager.setCurrentItem(currentItem);
                    break;

                case R.id.action_reviews:
                    currentItem = 2;
                    binding.pager.setCurrentItem(currentItem);
                    break;
            }
            return true;
        });

        binding.pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                binding.navigation.getMenu().getItem(currentItem).setChecked(true);
            }
        });
    }
}
