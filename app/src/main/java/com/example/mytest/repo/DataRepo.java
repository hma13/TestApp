package com.example.mytest.repo;

import com.example.mytest.data.CommitDetail;

import java.util.List;

import io.reactivex.Single;

public interface DataRepo {
    Single<List<CommitDetail>> getCommits(String owner, String repo, String branchName);
}
