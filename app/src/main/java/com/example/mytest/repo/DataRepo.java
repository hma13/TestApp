package com.example.mytest.repo;

import com.example.mytest.data.Commit;

import java.util.List;

import io.reactivex.Single;

public interface DataRepo {
    Single<List<Commit>> getCommits();
}
