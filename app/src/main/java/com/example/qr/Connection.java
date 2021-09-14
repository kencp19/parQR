package com.example.qr;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {

    private static final String BASE_URL = "http://118.97.30.43:9999/";
    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    static Retrofit retrofit = builder.build();

    public  static <S> S GetConnect(Class<S> ourClass){
        return retrofit.create(ourClass);
    }

}
