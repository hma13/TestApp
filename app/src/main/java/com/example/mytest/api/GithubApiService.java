/**
 *
 */

package com.example.mytest.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApiService {

    @GET("/repos/{owner}/{repo}/commits")
    Single<GetCommitsResponse> getCommits(@Path("owner") String owner, @Path("repo") String repo);
}
