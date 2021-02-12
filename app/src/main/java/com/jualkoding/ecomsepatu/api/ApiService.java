package com.jualkoding.ecomsepatu.api;

import com.jualkoding.ecomsepatu.model.cost.ItemCost;
import com.jualkoding.ecomsepatu.model.home.ItemProduk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("cost")
    Call<ItemCost> getCost(
            @Field("key") String token,
            @Field("android-key") String android,
            @Field("origin") String origin,
            @Field("destination") String destination,
            @Field("weight") String weight,
            @Field("courier") String courier
    );

    @GET("shop.php")
    Call<ItemProduk> getProduk(
            @Query("code_apps") String code_apps
    );

}
