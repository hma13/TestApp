package com.example.github.data;

import com.google.gson.annotations.SerializedName;

public class CommitListItem {
    private String sha;
    private String url;
    @SerializedName("commit")
    private CommitInfo commitInfo;

    public CommitListItem() {
    }

    public CommitListItem(String sha) {
        this.sha = sha;
    }

    public String getSha() {
        return sha;
    }

    public String getUrl() {
        return url;
    }

    public CommitInfo getCommitInfo() {
        return commitInfo;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "sha='" + sha + '\'' +
                ", url='" + url + '\'' +
                ", commitInfo=" + commitInfo +
                '}';
    }
}
