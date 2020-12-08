package net.juanxxiii.practica1t.interfaces;

import net.juanxxiii.practica1t.common.Constants;
import net.juanxxiii.practica1t.domain.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiDatosMadrid {
    @Headers({"Accept: application/json, Content-Type: application/json"})
    @GET(Constants.ENDPOINT_PISCINAS)
    Call<JsonResponse> getPoolList(@Query("latitud") long latitud, @Query("longitud") long longitud, @Query("distancia")int distancia);

    @Headers({"Accept: application/json, Content-Type: application/json"})
    @GET(Constants.ENDPOINT_CENTROS_DEPORTIVOS)
    Call<JsonResponse> getSportCenterList(@Query("latitud") long latitud, @Query("longitud") long longitud, @Query("distancia")int distancia);
}
