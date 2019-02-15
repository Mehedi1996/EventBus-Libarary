package com.example.busevent.NetworkRelatedClass;

import com.example.busevent.Util.Config;
import com.example.busevent.Model.DataReceiveEvent;

import org.greenrobot.eventbus.EventBus;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    public static void getData() {

        String myUrl = "https://raw.githubusercontent.com/hasancse91/EventBus-Android-Tutorial/master/Data/data.json";

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getDataFromServer(myUrl);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                EventBus.getDefault().post(new DataReceiveEvent(Config.DATA_RECEIVED, response.message()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                EventBus.getDefault().post(new DataReceiveEvent(Config.DATA_RECEIVED, t.toString()));
            }
        });

    }
}
