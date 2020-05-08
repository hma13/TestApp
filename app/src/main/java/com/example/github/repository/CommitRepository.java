package com.example.github.repository;

import com.example.github.AppSchedulers;
import com.example.github.api.GithubApiClient;
import com.example.github.data.CommitDetail;
import com.example.github.data.CommitListItem;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CommitRepository {
    private GithubApiClient client;
    private AppSchedulers schedulers;

    @Inject
    public CommitRepository(GithubApiClient client, AppSchedulers schedulers) {
        this.client = client;
        this.schedulers = schedulers;
    }

    public Single<List<CommitListItem>> getCommits(String owner, String repo, String branchName) {
        return client.getGitHubApiService().getCommits(owner, repo, branchName).subscribeOn(schedulers.network()).observeOn(schedulers.main());
    }

    public Single<CommitDetail> getCommit(String owner, String repo, String commitHash) {
        return client.getGitHubApiService().getCommit(owner, repo, commitHash).subscribeOn(schedulers.network()).observeOn(schedulers.main());
    }
}
