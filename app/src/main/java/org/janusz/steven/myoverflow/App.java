package org.janusz.steven.myoverflow;

import android.app.Application;

/**
 * Created by marek on 02.10.14.
 */
public class App extends Application {

    private String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
