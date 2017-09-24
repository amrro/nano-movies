package xyz.android.amrro.popularmovies.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.BaseActivity;
import xyz.android.amrro.popularmovies.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    private ActivityHomeBinding binding;
    public boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(binding.toolbar);
        animateToolbar(getString(R.string.sort_popularity_desc));

        if (findViewById(R.id.details_fragment) != null) {
            isTwoPane = true;
        }
    }

    void animateToolbar(String title) {
        View view = binding.toolbar.getChildAt(0);
        if (view != null && view instanceof TextView) {
            TextView titleTv = (TextView) view;
            titleTv.setAlpha(0f);
            titleTv.setScaleX(0.8f);
            titleTv.setText(title);
            titleTv.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .setStartDelay(300)
                    .setDuration(1000)
                    .setInterpolator(AnimationUtils.loadInterpolator(
                            this,
                            android.R.interpolator.fast_out_slow_in));
        }
    }

    void uptateTitle(@NonNull final String title) {

    }

    private void replaceTitle(Toolbar toolbar, @NonNull final String title) {
        Objects.requireNonNull(title);
        View view = toolbar.getChildAt(0);
        if (view != null && view instanceof TextView) {
            TextView titleTV = (TextView) view;
            // animate removing old title.
            titleTV.animate()
                    .alpha(0f)
                    .scaleX(0f)
                    .setStartDelay(300)
                    .setDuration(1000)
                    .setInterpolator(AnimationUtils.loadInterpolator(
                            this,
                            android.R.interpolator.fast_out_linear_in));
            // add the new.
            setTitle(title);

            // animate adding new title.
//            titleTV.setAlpha(0f);
//            titleTV.setScaleX(0.8f);
            titleTV.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .setStartDelay(300)
                    .setDuration(1000)
                    .setInterpolator(AnimationUtils.loadInterpolator(
                            this,
                            android.R.interpolator.fast_out_slow_in));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
