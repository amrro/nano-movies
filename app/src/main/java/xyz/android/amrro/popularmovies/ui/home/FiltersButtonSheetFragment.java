package xyz.android.amrro.popularmovies.ui.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import xyz.android.amrro.popularmovies.R;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/25/17.
 */

public class FiltersButtonSheetFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {


    private HomeFragment.FilterSelectionListener listener;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.filters_sheet, null);
        dialog.setContentView(contentView);


        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        contentView.findViewById(R.id.popularity).setOnClickListener(this);
        contentView.findViewById(R.id.favorites).setOnClickListener(this);
        contentView.findViewById(R.id.rating).setOnClickListener(this);
    }


    public void setListener(HomeFragment.FilterSelectionListener listener) {
        if (listener != null)
            this.listener = listener;
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
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss();
                    }

                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.popularity:
                listener.onFilterSelected(getString(R.string.sort_popularity_desc));
                break;
            case R.id.favorites:
                listener.onFilterSelected(getString(R.string.sort_favorites));
                break;
            case R.id.rating:
                listener.onFilterSelected(getString(R.string.sort_vot_count_desc));
                break;
        }
        dismiss();
    }
}
