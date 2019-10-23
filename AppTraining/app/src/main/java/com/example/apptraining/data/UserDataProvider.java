package com.example.apptraining.data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDataProvider {
    public static APIClient getAPIClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://generic-training.firebaseio.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIClient.class);
    }
}
