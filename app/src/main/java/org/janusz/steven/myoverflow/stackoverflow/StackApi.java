package org.janusz.steven.myoverflow.stackoverflow;

import org.janusz.steven.myoverflow.stackoverflow.data.ItemContainer;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by marek on 30.09.14.
 */
public interface StackApi {

    @GET("/2.2/questions/unanswered?order=desc&sort=activity&site=stackoverflow")
    void getUnansweredQuestions(
            @Header("Authorization") String bearerToken, Callback<ItemContainer> items
    );

    // not applicable...
    @FormUrlEncoded
    @POST("/oauth") void getAccessTokenAsync(
            @Field("username") String email,
            @Field("password") String password,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            Callback<AccessToken> token);
}
