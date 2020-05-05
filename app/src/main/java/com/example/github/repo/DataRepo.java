package com.example.github.repo;

import com.example.github.data.Commit;

import java.util.List;

import io.reactivex.Single;

public interface DataRepo {
    Single<List<Commit>> getCommits(String owner, String repo, String branchName);
}
