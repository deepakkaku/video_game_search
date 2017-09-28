package com.deepakkaku.videogamesearch.api;

import android.content.Context;

import com.deepakkaku.videogamesearch.BuildConfig;
import com.deepakkaku.videogamesearch.Utill.Config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Deepak Kaku on 28-09-2017.
 */

public class ApiClient {


  private Retrofit retrofit=null;

    private GiantBombService giantServices = null;
    private Context context;

    public ApiClient(Context context) {
        this.context = context;
    }


    public GiantBombService getGiantServices() {

        if(retrofit == null){
            OkHttpClient.Builder client = new OkHttpClient.Builder();
           /* if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                client.addInterceptor(logging);
            }*/

            retrofit = new Retrofit.Builder().client(client.build())
                                    .baseUrl(Config.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        giantServices = retrofit.create(GiantBombService.class);

        return giantServices;
    }

}
