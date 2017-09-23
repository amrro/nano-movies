package xyz.android.amrro.popularmovies.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.databinding.SimpleListBinding;

/**
 * A fragment representing a list of Items.
 */
public abstract class SimpleRecyclerFragment<M, A extends DataListAdapter> extends BaseFragment {
    private SimpleListBinding binding;
    protected A adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SimpleRecyclerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simple_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = Objects.requireNonNull(createAdapter());
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        setLoading(true);
        binding.setNoData(false);
        updateAdapter();
    }

    protected abstract A createAdapter();

    protected abstract void updateAdapter();

    protected void setLoading(final Boolean isLoading) {
        binding.setLoading(isLoading);
    }

    protected void setNoData(@StringRes int message, @DrawableRes int icon) {
        binding.setNoData(true);
        binding.noDataMessage.setText(message);
        binding.noDataIcon.setImageResource(icon);
    }
}
