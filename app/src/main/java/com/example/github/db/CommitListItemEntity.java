package com.example.github.db;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.github.data.CommitInfo;
import com.example.github.data.CommitListItem;

import java.util.Objects;

@Entity(tableName = "commit_list_item")
public class CommitListItemEntity extends BaseEntity {
    @PrimaryKey
    @NonNull
    private String sha;
    private String url;
    @Embedded
    private CommitInfo commitInfo;

    public CommitListItemEntity() {
    }

    @Ignore
    public CommitListItemEntity(@NonNull CommitListItem item) {
        this.sha = item.getSha();
        this.url = item.getUrl();
        CommitInfo commitInfo = item.getCommitInfo();
        if (commitInfo != null) {
            this.commitInfo = commitInfo;
        }

    }

    @Ignore
    public CommitListItemEntity(String sha) {
        this.sha = sha;
    }

    public String getSha() {
        return sha;
    }

    public String getUrl() {
        return url;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CommitInfo getCommitInfo() {
        return commitInfo;
    }

    public void setCommitInfo(CommitInfo commitInfo) {
        this.commitInfo = commitInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitListItemEntity that = (CommitListItemEntity) o;
        return sha.equals(that.sha) &&
                url.equals(that.url) &&
                Objects.equals(commitInfo, that.commitInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha, url, commitInfo);
    }

    public String getCommitterName() {
        if (commitInfo != null && commitInfo.getCommitter() != null) {
            return commitInfo.getCommitter().getName();
        }
        return null;
    }

    public String getMessage() {
        if (commitInfo != null) {
            return commitInfo.getMessage();
        }
        return null;
    }

    public void setMessage(String message) {
        if (commitInfo != null) {
            commitInfo.setMessage(message);
        }
    }
}
