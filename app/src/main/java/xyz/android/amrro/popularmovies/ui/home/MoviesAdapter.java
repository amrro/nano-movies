package xyz.android.amrro.popularmovies.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.model.Result;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 7/23/17.
 */

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    @NonNull
    private final Context context;

    @NonNull
    private ArrayList<Result> results;

    public MoviesAdapter(@NonNull Context context, @NonNull ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.card_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Glide.with(context)
                .load(results.get(position).getPosterPath())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public final class MovieViewHolder extends RecyclerView.ViewHolder {

        final ImageView poster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}

