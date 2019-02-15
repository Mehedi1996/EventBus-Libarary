package com.example.busevent.NetworkRelatedClass;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<ResponseBody> getDataFromServer(@Url String url);
}

