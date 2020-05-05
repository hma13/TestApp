package com.example.mytest.data;

import com.google.gson.annotations.SerializedName;

public class Commit {
    private String sha;
    private String url;
    @SerializedName("commit")
    private CommitDetail commitDetail;

}
