package org.janusz.steven.myoverflow.stackoverflow;

import retrofit.RestAdapter;

/**
 * Created by marek on 30.09.14.
 */
public class StackOverflowRestAdapter {
    private static RestAdapter restAdapter;

    public static RestAdapter getAdapter() {
        synchronized (StackOverflowRestAdapter.class) {
            if (restAdapter == null)
                restAdapter = new RestAdapter.Builder().setEndpoint("https://stackexchange.com").build();
            return restAdapter;
        }
    }
}
