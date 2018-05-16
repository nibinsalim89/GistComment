package com.gist.nibinsalim.gistcomment.retrofit;


/*
 * @class RetrofitClientConfig
 * @author Nibin Salim
 * @Description class handles the initial configuration for retrofit is done
 */
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientConfig {

    private static final String BASE_URL = "https://api.github.com/gists/";
    private static Retrofit RETROFIT;

    public static Retrofit getInstance(String userName, String password) {
        if(null == RETROFIT) {
            // set your desired log level
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            // add your other interceptors
            httpClient.addInterceptor(new AuthenticationInterceptor(userName, password));  // <-- this is the important line!
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return RETROFIT;
    }

}
