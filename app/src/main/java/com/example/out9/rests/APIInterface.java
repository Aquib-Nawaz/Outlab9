package com.example.out9.rests;
import com.example.out9.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface APIInterface {
    @GET("everything")
    Call<ResponseModel> getLatestNews(@Query("sources") String source,@Query("language") String lang, @Query("apiKey") String apiKey);
}
