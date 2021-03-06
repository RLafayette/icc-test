package br.com.icc.service;

import java.util.HashMap;

import org.springframework.data.util.Pair;

import br.com.icc.model.HostPort;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface StockManagerService {
    @GET("stock")
    Call<HashMap<String,String>> getStocks();
    
    @Headers("Content-Type: application/json")
    @POST("notification")
    Call<Pair<String,Integer>> subscribe(@Body HostPort hostPort);

}

