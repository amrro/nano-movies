package xyz.android.amrro.popularmovies.common;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 9/20/17.
 * Mother of all {@link Fragment}s
 */

public class BaseFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @SuppressWarnings("ConstantConditions")
    protected void snack(@NonNull final String message) {
        Objects.requireNonNull(message);
        Snackbar
                .make(getView(), Objects.requireNonNull(message), Snackbar.LENGTH_LONG)
                .setAction(android.R.string.ok, null)
                .show();
    }

    protected <T extends ViewModel> T getViewModel(final Class<T> cls) {
        return ViewModelProviders.of(this, viewModelFactory).get(cls);
    }

    @NonNull
    protected Integer itemId() {
        return getArguments().getInt(Navigator.KEY_ITEM_ID, - 1);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }
}
