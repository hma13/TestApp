package com.example.github.ui.commit;

import androidx.annotation.Nullable;

import com.example.github.data.CommitDetail;
import com.example.github.data.CommitFile;

import java.util.ArrayList;
import java.util.List;

public class CommitDetailListItem {
    private String name;
    private String message;

    public CommitDetailListItem(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    static List<CommitDetailListItem> build(@Nullable CommitDetail commitDetail) {
        List<CommitDetailListItem> list = new ArrayList<>();
        if (commitDetail != null) {
            list.add(new CommitDetailListItem("SHA", commitDetail.getSha()));
            list.add(new CommitDetailListItem("Committer", commitDetail.getCommitInfo().getAuthorName()));
            List<CommitFile> files = commitDetail.getFiles();
            if (files != null && !files.isEmpty()) {
                for (CommitFile file : files) {
                    list.add(new CommitDetailListItem(file.getFileName(), file.getStatus()));
                }
            }

        }
        return list;
    }
}
