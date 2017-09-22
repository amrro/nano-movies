package xyz.android.amrro.popularmovies.common;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by amrro <amr.elghobary@gmail.com> on 8/29/17.
 * <p>
 * Holds the common properties of all {@link android.app.Activity}s.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    public Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        navigator = new Navigator(this);
        String itemId = savedInstanceState != null ?
                savedInstanceState.getString(Navigator.KEY_ITEM_ID)
                : getIntent().getStringExtra(Navigator.KEY_ITEM_ID);
    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        if (itemId() != null) {
            outState.putString(Navigator.KEY_ITEM_ID, itemId);
        }
        super.onSaveInstanceState(outState);
    }*/

    protected void toast(@NonNull final String message) {
        Objects.requireNonNull(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void setTitle(@NonNull final String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Objects.requireNonNull(title));
        }
    }

    protected void dismissKeyboard(IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }

    protected Integer itemId() {
        return getIntent().getIntExtra(Navigator.KEY_ITEM_ID, - 1);
    }
}
