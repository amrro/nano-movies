package xyz.android.amrro.popularmovies.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import xyz.android.amrro.popularmovies.R;
import xyz.android.amrro.popularmovies.data.api.MoviesService;
import xyz.android.amrro.popularmovies.data.model.Movie;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    @Inject
    MoviesService api;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (api != null) {
           api.movie(263115).enqueue(
                   new Callback<Movie>() {
                       @Override
                       public void onResponse(Call<Movie> call, Response<Movie> response) {
                           if (response.isSuccessful()) {
                               Timber.i(response.body().toString());
                           }
                       }

                       @Override
                       public void onFailure(Call<Movie> call, Throwable t) {
                            Timber.e(t.getMessage());
                       }
                   }
           );
        }
    }

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }
}
