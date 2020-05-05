/**
 *
 */

package com.example.mytest.api;

import com.example.mytest.data.Commit;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApiService {

    @GET("/repos/{owner}/{repo}/commits")
    Single<List<Commit>> getCommits(@Path("owner") String owner, @Path("repo") String repo, @Query("sha") String branchName);
}
