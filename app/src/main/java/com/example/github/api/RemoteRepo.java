package com.example.github.api;

import com.example.github.data.CommitDetail;
import com.example.github.data.CommitListItem;
import com.example.github.repo.DataRepo;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Single;

@Reusable
public class RemoteRepo implements DataRepo {
    private GithubApiClient client;

    @Inject
    public RemoteRepo(GithubApiClient client) {
        this.client = client;
    }

    @Override
    public Single<List<CommitListItem>> getCommits(String owner, String repo, String branchName) {
        return client.getGitHubApiService().getCommits(owner, repo, branchName);
    }

    @Override
    public Single<CommitDetail> getCommit(String owner, String repo, String commitHash) {
        return client.getGitHubApiService().getCommit(owner, repo, commitHash);
    }
}
