package com.example.github.repository;

import androidx.annotation.WorkerThread;

import com.example.github.AppSchedulers;
import com.example.github.api.GithubApiService;
import com.example.github.data.CommitDetail;
import com.example.github.db.CommitListItemEntity;
import com.example.github.db.CommitListItemEntityDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

@Singleton
public class CommitRepository {
    private GithubApiService githubApiService;
    private AppSchedulers schedulers;
    private CommitListItemEntityDao dao;

    @Inject
    public CommitRepository(GithubApiService service, AppSchedulers schedulers, CommitListItemEntityDao dao) {
        this.githubApiService = service;
        this.schedulers = schedulers;
        this.dao = dao;
    }

    @WorkerThread
    public Single<List<CommitListItemEntity>> getCommits(String owner, String repo, String branchName, boolean force) {
        if (force) {
            return getRemoteCommits(owner, repo, branchName);
        } else {
            return getLocalCommits(owner, repo, branchName).flatMap(commitListItemEntities -> {
                if (commitListItemEntities.size() == 0) {
                    return getRemoteCommits(owner, repo, branchName)
                            .observeOn(schedulers.single())
                            .flatMapCompletable(remotes -> dao.insert(remotes.toArray(new CommitListItemEntity[0])))
                            .andThen(dao.getCommits());
                } else {
                    return Single.just(commitListItemEntities);
                }
            });
        }
    }

    private Single<List<CommitListItemEntity>> getLocalCommits(String owner, String repo, String branchName) {
        return dao.getCommits().subscribeOn(schedulers.single());

    }

    private Single<List<CommitListItemEntity>> getRemoteCommits(String owner, String repo, String branchName) {
        return githubApiService.getCommits(owner, repo, branchName)
                .subscribeOn(schedulers.network())
                .filter(Response::isSuccessful)
                .map(Response::body)
                .flattenAsObservable(commitListItems -> commitListItems)
                .map(CommitListItemEntity::new)
                .toList();
    }

    public Single<Response<CommitDetail>> getCommit(String owner, String repo, String commitHash) {
        return githubApiService.getCommit(owner, repo, commitHash).subscribeOn(schedulers.network()).observeOn(schedulers.main());
    }
}
