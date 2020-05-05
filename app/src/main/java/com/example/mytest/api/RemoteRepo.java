package com.example.mytest.api;

import com.example.mytest.data.CommitDetail;
import com.example.mytest.repo.DataRepo;

import java.util.Arrays;
import java.util.List;

import dagger.Reusable;
import io.reactivex.Single;
import timber.log.Timber;

@Reusable
public class RemoteRepo implements DataRepo {
    private GithubApiClient client;

    public RemoteRepo(GithubApiClient client) {
        this.client = client;
    }

    @Override
    public Single<List<CommitDetail>> getCommits(String owner, String repo, String branchName) {
        return client.getGitHubApiService().getCommits(owner, repo, branchName).doAfterSuccess(commitDetails -> Timber.d(Arrays.toString(commitDetails.toArray(new CommitDetail[0]))));
    }
}
