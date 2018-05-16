package com.gist.nibinsalim.gistcomment.retrofit;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * @class AuthenticationInterceptor
 * @author Nibin Salim
 * @Description Adds the basic authentication(username and password) required for gist
 */

public class AuthenticationInterceptor implements Interceptor {

    private String credentials;

    public AuthenticationInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }

}