package com.deazzle.deazzleassignment.api;

import com.deazzle.deazzleassignment.model.Example;
import com.deazzle.deazzleassignment.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/")
    Call<Example> getResultData(@Query("results")Integer results);
}
