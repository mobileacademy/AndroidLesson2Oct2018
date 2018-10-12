package ro.mobileacademy.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrei on 05/10/2018.
 * 2018
 */
public class NetworkManager {

    private static final String BASE_URL = "https://api.github.com/";

    private static final NetworkManager ourInstance = new NetworkManager();

    private GitHubService service;

    public static NetworkManager getInstance() {
        return ourInstance;
    }

    private NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);
    }

    public GitHubService getService() {
        return service;
    }

}
