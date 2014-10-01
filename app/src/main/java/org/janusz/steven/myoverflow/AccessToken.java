package org.janusz.steven.myoverflow;

import com.google.gson.annotations.SerializedName;

import retrofit.http.Field;

/**
 * Created by marek on 30.09.14.
 */
public class AccessToken {
    @SerializedName("access_token") String accessToken;
    @SerializedName("refresh_token") String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
