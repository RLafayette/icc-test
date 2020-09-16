package br.com.icc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.icc.model.HostPort;
import br.com.icc.repository.StockCache;
import br.com.icc.service.StockCacheService;
import br.com.icc.service.StockManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigureAfterStartup {

	@Autowired
	public static StockCacheService cacheService;
	@Autowired
	public static StockManagerService stockService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void setupAfterStartup() {
		
		StockCache.getInstance();
		
		Gson gson = new GsonBuilder()
                .setDateFormat("E, d MMM yyyy HH:mm:ss Z")
                .create();
		
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        stockService = retrofit.create(StockManagerService.class);
		
		//TODO: Test the connection
		Call<Pair<String,Integer>> call = stockService.subscribe(new HostPort("localhost", new Integer(8081)));
		
        call.enqueue(new Callback<Pair<String,Integer>>() {
            @Override
            public void onResponse(Call<Pair<String,Integer>> call, Response<Pair<String,Integer>> response) {   	
        		cacheService.fillCache();
            }
            @Override
            public void onFailure(Call<Pair<String,Integer>> call, Throwable t) {
            	t.printStackTrace();
            }
        });
	}
}
