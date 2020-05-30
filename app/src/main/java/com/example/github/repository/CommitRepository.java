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

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class CommitRepository {
    private GithubApiService githubApiService;
    private AppSchedulers schedulers;
    private CommitListItemEntityDao dao;
    private Flowable<List<CommitListItemEntity>> localFlowable;
    private Flowable<List<CommitListItemEntity>> commitsFlowable;
    private Flowable<List<CommitListItemEntity>> remoteFlowable;

    @Inject
    public CommitRepository(GithubApiService service, AppSchedulers schedulers, CommitListItemEntityDao dao) {
        this.githubApiService = service;
        this.schedulers = schedulers;
        this.dao = dao;
    }

    @WorkerThread
    public Flowable<List<CommitListItemEntity>> getCommits(String owner, String repo, String branchName) {
        if (commitsFlowable == null) {
            commitsFlowable = Flowable.merge(getLocalCommits(), getRemoteCommits(owner, repo, branchName))
                    .filter(commitListItemEntities -> !commitListItemEntities.isEmpty())
                    .take(1);
        }
        return commitsFlowable;
    }

    private Flowable<List<CommitListItemEntity>> getLocalCommits() {
        Timber.d("Querying DB");
        if (localFlowable == null) {
            localFlowable = dao.getCommits().distinctUntilChanged();
        }
        return localFlowable.subscribeOn(schedulers.single());
    }

    @WorkerThread
    public Flowable<List<CommitListItemEntity>> getRemoteCommits(String owner, String repo, String branchName) {
        Timber.d("Calling API");
        if (remoteFlowable == null) {
            remoteFlowable = githubApiService.getCommits(owner, repo, branchName)
                    .subscribeOn(schedulers.network())
                    .filter(Response::isSuccessful)
                    .map(Response::body)
                    .flattenAsObservable(commitListItems -> commitListItems)
                    .map(CommitListItemEntity::new)
                    .toList()
                    .flatMapCompletable(remotes -> {
                        CommitListItemEntity[] array = remotes.toArray(new CommitListItemEntity[0]);
                        return dao.insert(array);
                    })
                    .andThen(getLocalCommits());

        }
        return remoteFlowable;
    }

    public Single<Response<CommitDetail>> getCommit(String owner, String repo, String commitHash) {
        return githubApiService.getCommit(owner, repo, commitHash).subscribeOn(schedulers.network()).observeOn(schedulers.main());
    }

    @WorkerThread
    public Completable update(String sha) {
        return dao.getCommitBySha(sha)
                .subscribeOn(schedulers.single())
                .map(commitListItemEntity -> {
                    Timber.d(commitListItemEntity.getSha());
                    commitListItemEntity.setMessage("Updated");
                    return commitListItemEntity;
                })
                .flatMapCompletable(entity -> dao.update(entity))
                .doOnComplete(() -> Timber.d("update completed"));
    }
}
