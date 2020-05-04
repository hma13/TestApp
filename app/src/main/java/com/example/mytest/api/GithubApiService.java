/**
 *
 */

package com.example.mytest.api;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface GithubApiService {

    @GET("/repos/:owner/:repo/commits")
    Single<GetCommitsResponse> getCommits();
}
