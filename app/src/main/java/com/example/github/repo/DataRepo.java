package com.example.github.repo;

import com.example.github.data.CommitDetail;
import com.example.github.data.CommitListItem;

import java.util.List;

import io.reactivex.Single;

public interface DataRepo {
    Single<List<CommitListItem>> getCommits(String owner, String repo, String branchName);

    Single<CommitDetail> getCommit(String owner, String repo, String commitHash);
}
