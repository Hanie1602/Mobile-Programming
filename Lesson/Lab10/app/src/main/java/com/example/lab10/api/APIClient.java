package com.example.lab10.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL = "https://68593083138a18086dfd6c76.mockapi.io/"; //URL chính của API
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build(); //Tự động parse JSON thành Trainee class
        }
        return retrofit;
    }
}
