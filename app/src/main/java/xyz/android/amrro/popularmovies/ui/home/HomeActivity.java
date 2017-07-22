package xyz.android.amrro.popularmovies.ui.home;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import xyz.android.amrro.popularmovies.R;

public class HomeActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector;

    @Inject
    SharedPreferences prfs;

    /**
     * indicates if the device is connected to network or not.
     */
    boolean connected = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*drawer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (prfs != null) {
            Toast.makeText(this, "prfs is not null", Toast.LENGTH_SHORT).show();
        }

        animateToolbar(toolbar);
    }

    private void animateToolbar(Toolbar toolbar) {
        View view = toolbar.getChildAt(0);
        if (view != null && view instanceof TextView) {
            TextView title = (TextView) view;

            // fade in and space out the title.  Animating the letterSpacing performs horribly so
            // fake it by setting the desired letterSpacing then animating the scaleX ¯\_(ツ)_/¯
            title.setAlpha(0f);
            title.setScaleX(0.8f);

            title.animate()
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkConnectivity() {
        // TODO: 7/22/17 check if there is a connection or not.
    }

    private ConnectivityManager.NetworkCallback connectivityCallback
            = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            connected = true;
            // TODO: 7/22/17 Connection is active, do your home work.
        }

        @Override
        public void onLost(Network network) {
            connected = false;
        }
    };


    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
