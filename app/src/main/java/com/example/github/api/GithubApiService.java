package com.example.github.api;

import com.example.github.data.CommitDetail;
import com.example.github.data.CommitListItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApiService {

    @GET("/repos/{owner}/{repo}/commits?per_page=50")
    Single<Response<List<CommitListItem>>> getCommits(@Path("owner") String owner, @Path("repo") String repo, @Query("sha") String branchName);

    @GET("/repos/{owner}/{repo}/commits/{commitHash}")
    Single<Response<CommitDetail>> getCommit(@Path("owner") String owner, @Path("repo") String repo, @Path("commitHash") String commitHash);
}
