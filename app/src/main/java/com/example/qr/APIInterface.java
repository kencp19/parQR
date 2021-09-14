package com.example.qr;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("gw_qr/inquery_plat.php")
    Call<KendaraanResponse> kendaraan(@Body KendaraanRequest kendaraanRequest);


}
