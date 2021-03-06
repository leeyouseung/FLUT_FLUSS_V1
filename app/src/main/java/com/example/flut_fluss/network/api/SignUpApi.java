package com.example.flut_fluss.network.api;

import com.example.flut_fluss.network.request.SignUpRequest;
import com.example.flut_fluss.network.Response;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpApi {

    @POST("/api/user")
    Single<retrofit2.Response<Response>> signup(@Body SignUpRequest signUpRequest);
}
