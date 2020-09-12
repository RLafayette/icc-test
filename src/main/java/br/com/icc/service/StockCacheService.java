package br.com.icc.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.icc.repository.StockCache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class StockCacheService {

	@Autowired
	private static StockManagerService service;
	
	public void deleteCache() {
		StockCache.getInstance().getStocksKeys().clear();
	}

	public HashMap<String,String> fillCache(HashMap<String,String> newCache) {
		StockCache.getInstance().setStocksKeys(newCache);
		return StockCache.getInstance().getStocksKeys();
	}
	
	public boolean find(String stockId) {
		return StockCache.getInstance().getStocksKeys().keySet().contains(stockId);
	}
	
	public void fillCache() {
		Call<HashMap<String,String>> call = service.getStocks();
        
		Gson gson = new GsonBuilder()
                .setDateFormat("E, d MMM yyyy HH:mm:ss Z")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(StockManagerService.class);
		
		
        call.enqueue(new Callback<HashMap<String,String>>() {
            @Override
            public void onResponse(Call<HashMap<String,String>> call, Response<HashMap<String,String>> response) {
            	StockCache.getInstance().setStocksKeys(response.body());
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
            	//TODO: Tratar isso.
            }
        });
	}

}
