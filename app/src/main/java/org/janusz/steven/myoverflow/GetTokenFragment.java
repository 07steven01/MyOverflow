package org.janusz.steven.myoverflow;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

/**
 * Created by marek on 01.10.14.
 */
public class GetTokenFragment extends Fragment {

    @InjectView(R.id.text)
    TextView mText;
    @InjectView(R.id.webview)
    WebView mWebview;

    public GetTokenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);

        getAuthToken();
        return rootView;
    }

    public interface TokenReceivedListener {
        public void onTokenReceived(String token);
    }

    private void getAuthToken() {
        mWebview.setVisibility(View.VISIBLE);
        WebViewClient client = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String accessToken = parseUrlForToken(url);
                Timber.d("Loaded url " + url);
                Timber.d("access token: " + accessToken);
                if (accessToken != null)
                    ((TokenReceivedListener)getActivity()).onTokenReceived(accessToken);
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

    private String parseUrlForToken(String url) {

        String accessTokenKey = "access_token=";
        int indexOfAmpersand = url.indexOf("&");
        return url.substring(url.indexOf(accessTokenKey)+accessTokenKey.length(),
                indexOfAmpersand != -1 ? indexOfAmpersand : url.length());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
