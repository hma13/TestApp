package com.example.github.repository;

import com.example.github.AppSchedulers;
import com.example.github.api.GithubApiService;
import com.example.github.data.CommitDetail;
import com.example.github.data.CommitListItem;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

@Singleton
public class CommitRepository {
    private GithubApiService githubApiService;
    private AppSchedulers schedulers;

    @Inject
    public CommitRepository(GithubApiService service, AppSchedulers schedulers) {
        this.githubApiService = service;
        this.schedulers = schedulers;
    }

    public Single<Response<List<CommitListItem>>> getCommits(String owner, String repo, String branchName) {
        return githubApiService.getCommits(owner, repo, branchName).subscribeOn(schedulers.network()).observeOn(schedulers.main());
    }

    public Single<Response<CommitDetail>> getCommit(String owner, String repo, String commitHash) {
        return githubApiService.getCommit(owner, repo, commitHash).subscribeOn(schedulers.network()).observeOn(schedulers.main());
    }
}
