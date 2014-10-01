package org.janusz.steven.myoverflow;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @InjectView(R.id.text)
        TextView mText;
        @InjectView(R.id.webview)
        WebView mWebview;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.inject(this, rootView);

            getAuthToken();
            return rootView;
        }

        private void getAuthToken() {
            mWebview.setVisibility(View.VISIBLE);
            WebViewClient client = new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    Timber.d("Loaded url " + url);
                }
            };
            mWebview.setWebViewClient(client);
            mWebview.getSettings().setJavaScriptEnabled(true);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPreExecute() {
                    mWebview.setVisibility(View.INVISIBLE);
                    mText.setText("Loading...");
                    mText.setVisibility(View.VISIBLE);
                }

                @Override
                protected Void doInBackground(Void... params) {
                    mWebview.loadUrl("https://stackexchange.com/oauth/dialog?client_id="
                            + "3646" + "&scope=" + "read_inbox" + "&redirect_uri=" +
                            "https://stackexchange.com/oauth/login_success");
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    mText.setVisibility(View.INVISIBLE);
                    mWebview.setVisibility(View.VISIBLE);
                }
            }.execute();

        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.reset(this);
            ButterKnife.reset(this);
        }
    }
}
