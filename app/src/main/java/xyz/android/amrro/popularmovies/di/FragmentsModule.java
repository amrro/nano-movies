package xyz.android.amrro.popularmovies.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import xyz.android.amrro.popularmovies.ui.movie.MovieDetailsFragment;
import xyz.android.amrro.popularmovies.ui.movie.pager.MoviePagerFragment;
import xyz.android.amrro.popularmovies.ui.movie.review.ReviewsFragment;
import xyz.android.amrro.popularmovies.ui.movie.trailer.TrailersFragment;

@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    public abstract MoviePagerFragment contributesMoviePagerFragment();

    @ContributesAndroidInjector
    public abstract ReviewsFragment contributesReviewsFragment();

    @ContributesAndroidInjector
    public abstract TrailersFragment contributesTrailersFragment();

    @ContributesAndroidInjector
    public abstract MovieDetailsFragment contributesMovieDetailsFragment();
}
