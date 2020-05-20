package com.example.github.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public abstract class CommitListItemEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(CommitListItemEntity... entities);
}
