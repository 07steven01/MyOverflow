package org.janusz.steven.myoverflow.stackoverflow;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

/**
 * Created by marek on 30.09.14.
 */
public class StackOverflowRestAdapter {
    private static RestAdapter restAdapter;

    public static RestAdapter getAdapter() {
        synchronized (StackOverflowRestAdapter.class) {
            if (restAdapter == null)
                restAdapter = new RestAdapter.Builder().setEndpoint("https://api.stackexchange.com").
                        setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("JANUSZ RETROFIT")).build();
            return restAdapter;
        }
    }
}
