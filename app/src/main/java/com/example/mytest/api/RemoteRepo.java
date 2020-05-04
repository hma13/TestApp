package com.example.mytest.api;

import com.example.mytest.data.Commit;
import com.example.mytest.repo.DataRepo;

import java.util.List;

import dagger.Lazy;
import dagger.Reusable;
import io.reactivex.Single;

@Reusable
public class RemoteRepo implements DataRepo {
    private Lazy<GithubApiClient> clientLazy;

    public RemoteRepo(Lazy<GithubApiClient> clientLazy) {
        this.clientLazy = clientLazy;
    }

    @Override
    public Single<List<Commit>> getCommits() {
        return clientLazy.get().getGitHubApiService().getCommits().map(GetCommitsResponse::getCommits);
    }
}
