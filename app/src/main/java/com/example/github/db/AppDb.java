package com.example.github.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {CommitListItemEntity.class/*, CommitDetail.class*/},
        version = 1
)
//@TypeConverters(AppTypeConverters.class)
public abstract class AppDb extends RoomDatabase {
    abstract CommitListItemEntityDao commitListItemEntityDao();
//    abstract CommitDetailDao commitDetailDao();
}
