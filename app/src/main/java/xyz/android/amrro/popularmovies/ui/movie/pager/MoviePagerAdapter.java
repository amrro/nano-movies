package xyz.android.amrro.popularmovies.ui.movie.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsFragment;
import xyz.android.amrro.popularmovies.ui.movie.review.ReviewsFragment;
import xyz.android.amrro.popularmovies.ui.movie.trailer.TrailersFragment;


public final class MoviePagerAdapter extends FragmentStatePagerAdapter {
    private final Integer id;

    MoviePagerAdapter(FragmentManager fm, final Integer id) {
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MovieDetailsFragment.newInstance(id);
            case 1:
                return TrailersFragment.newInstance(id);
            case 2:
                return ReviewsFragment.newInstance(id);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Info";
            case 1:
                return "Trailers";
            case 2:
                return "Reviews";
            default:
                return null;

        }

    }
}
