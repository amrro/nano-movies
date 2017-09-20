package xyz.android.amrro.popularmovies.ui.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.common.OnItemClickedListener;
import xyz.android.amrro.popularmovies.databinding.FiltersSheetBinding;
import xyz.android.amrro.popularmovies.utils.FiltersHandler;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/25/17.
 * Bottom Sheet prompts the user to change the filters of movies in {@link HomeActivity}.
 */

public class FiltersButtonSheetFragment extends BottomSheetDialogFragment {
    private OnItemClickedListener<String> listener;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.filters_sheet, null);
        FiltersSheetBinding binding = DataBindingUtil.bind(contentView);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        binding.setHandler(new FiltersHandler() {
            @Override
            public void popular(View view) {
                changeFilter(R.string.sort_popularity_desc);
            }

            @Override
            public void favorites(View view) {
                changeFilter(R.string.sort_favorites);
            }

            @Override
            public void rating(View view) {
                changeFilter(R.string.sort_vot_count_desc);
            }
        });
    }


    public void setListener(OnItemClickedListener<String> listener) {
        if (listener != null)
            this.listener = listener;
    }

    private void changeFilter(@StringRes int filerId) {
        if (listener == null) {
            throw new IllegalStateException("You have to set the listener first.");
        }
        listener.onClicked(getString(filerId));
        dismiss();
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        listener = null;
        super.onDismiss(dialog);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback =
            new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) dismiss();
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            };
}
