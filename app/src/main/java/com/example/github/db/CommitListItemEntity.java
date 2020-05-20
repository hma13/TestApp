package com.example.github.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.github.data.CommitInfo;
import com.example.github.data.CommitListItem;

@Entity
public class CommitListItemEntity {
    @PrimaryKey
    @NonNull
    private String sha;
    private String url;
    private String authorName;
    private String authorEmail;
    private Long authorDate;
    private String committerName;
    private String committerEmail;
    private Long committerDate;
    private String message;

    public CommitListItemEntity() {
    }

    @Ignore
    public CommitListItemEntity(@NonNull CommitListItem item) {
        this.sha = item.getSha();
        this.url = item.getUrl();
        CommitInfo commitInfo = item.getCommitInfo();
        if (commitInfo != null) {
            this.message = commitInfo.getMessage();
            CommitInfo.PersonalInfo author = commitInfo.getAuthor();
            if (author != null) {
                authorName = author.getName();
                authorEmail = author.getEmail();
                authorDate = author.getDate();
            }

            CommitInfo.PersonalInfo committer = commitInfo.getCommitter();
            if (committer != null) {
                committerName = committer.getName();
                committerEmail = committer.getEmail();
                committerDate = committer.getDate();
            }
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

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public Long getAuthorDate() {
        return authorDate;
    }

    public String getCommitterName() {
        return committerName;
    }

    public String getCommitterEmail() {
        return committerEmail;
    }

    public Long getCommitterDate() {
        return committerDate;
    }

    public String getMessage() {
        return message;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public void setAuthorDate(Long authorDate) {
        this.authorDate = authorDate;
    }

    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    public void setCommitterEmail(String committerEmail) {
        this.committerEmail = committerEmail;
    }

    public void setCommitterDate(Long committerDate) {
        this.committerDate = committerDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
