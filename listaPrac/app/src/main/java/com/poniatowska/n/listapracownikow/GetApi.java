package com.poniatowska.n.listapracownikow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetApi {
    @GET("api/v1/employees")
    Call<List<Person>> getData();
}
