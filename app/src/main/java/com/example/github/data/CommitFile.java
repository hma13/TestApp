package com.example.github.data;

import com.google.gson.annotations.SerializedName;

/**
 * sha: "debce2e5a77801eea52cd4b0b152273bfde02594",
 * filename: "README.md",
 * status: "added",
 * additions: 2,
 * deletions: 0,
 * changes: 2,
 * blob_url: "https://github.com/hma13/TestApp/blob/0eab353fda204632e34d14cc7043ffdbe56baf95/README.md",
 * raw_url: "https://github.com/hma13/TestApp/raw/0eab353fda204632e34d14cc7043ffdbe56baf95/README.md",
 * contents_url: "https://api.github.com/repos/hma13/TestApp/contents/README.md?ref=0eab353fda204632e34d14cc7043ffdbe56baf95",
 * patch: "@@ -0,0 +1,2 @@ +# MyApp +A demo Android app in Java using Dagger2, ViewModel, RxJava, Retrofit, unit test and Espresso test. Integrated with Github API to get data."
 */
public class CommitFile {
    private String sha;
    @SerializedName("filename")
    private String fileName;
    private String status;
    @SerializedName("raw_url")
    private String rawUrl;

    public String getSha() {
        return sha;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStatus() {
        return status;
    }

    public String getRawUrl() {
        return rawUrl;
    }

    @Override
    public String toString() {
        return "CommitFile{" +
                "sha='" + sha + '\'' +
                ", fileName='" + fileName + '\'' +
                ", status='" + status + '\'' +
                ", rawUrl='" + rawUrl + '\'' +
                '}';
    }
}
