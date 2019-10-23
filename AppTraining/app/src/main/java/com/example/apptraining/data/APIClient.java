package com.example.apptraining.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIClient {
    @GET("/users.json")
    Call<List<User>> getAllUsers();

    @GET("/users/{id}.json")
    Call<User> getUserWithId(@Path("id") String userId);

    //@POST("/users.json")
    //Call<User> createUser(@Body User user);
}
