package ro.mobileacademy.myapplication.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ro.mobileacademy.myapplication.model.Contributor;
import ro.mobileacademy.myapplication.model.Repo;

/**
 * Created by andrei on 05/10/2018.
 * 2018
 */
public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> listContributors(@Path("owner") String owner, @Path("repo") String repo);
}
