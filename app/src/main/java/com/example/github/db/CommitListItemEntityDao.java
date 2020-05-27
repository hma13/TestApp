package com.example.github.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface CommitListItemEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(CommitListItemEntity... entities);

    @Query("SELECT * FROM commit_list_item")
    Single<List<CommitListItemEntity>> getCommits();
}
