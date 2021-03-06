package org.janusz.steven.myoverflow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import timber.log.Timber;


public class MainActivity extends Activity implements GetTokenFragment.TokenReceivedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new GetTokenFragment())
                    .commit();
        }
        Timber.plant(new Timber.DebugTree());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTokenReceived(String token) {
        // TODO open fragment to present api call results: get all unanswered
        ((App) getApplication()).setAccessToken(token);
        getFragmentManager().beginTransaction().replace(R.id.container,
                new MyOverflowResultsFragment()).
                commit();
    }



}
