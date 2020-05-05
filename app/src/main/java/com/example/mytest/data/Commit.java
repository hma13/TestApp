package com.example.mytest.data;

import com.google.gson.annotations.SerializedName;

public class Commit {
    private String sha;
    private String url;
    @SerializedName("commit")
    private CommitDetail commitDetail;

    public String getSha() {
        return sha;
    }

    public String getUrl() {
        return url;
    }

    public CommitDetail getCommitDetail() {
        return commitDetail;
    }
}
