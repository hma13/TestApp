package com.example.github.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommitDetail {
    private String sha;
    private String url;
    @SerializedName("commit")
    private CommitInfo commitInfo;
    private List<CommitFile> files;

    public CommitDetail() {
    }

    public CommitDetail(String sha) {
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

    public List<CommitFile> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "CommitDetail{" +
                "sha='" + sha + '\'' +
                ", url='" + url + '\'' +
                ", commitInfo=" + commitInfo +
                ", files=" + files +
                '}';
    }
}
