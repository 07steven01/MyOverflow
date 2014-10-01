package org.janusz.steven.myoverflow;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by marek on 30.09.14.
 */
public interface StackApi {

    @FormUrlEncoded
    @POST("/oauth") void getAccessTokenAsync(
            @Field("username") String email,
            @Field("password") String password,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            Callback<AccessToken> token);
}
