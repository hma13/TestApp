package com.example.mytest.data;

/**
 * "commit": {
 * "author": {
 * "name": "Hua Ma",
 * "email": "hua.ma@gm.com",
 * "date": "2020-05-04T22:11:14Z"
 * },
 * "committer": {
 * "name": "Hua Ma",
 * "email": "hua.ma@gm.com",
 * "date": "2020-05-04T22:11:14Z"
 * },
 * "message": "init commit.",
 * "tree": {
 * "sha": "03579b3ddb0d7fc3b74fc7ee75ec49635bdea6ac",
 * "url": "https://api.github.com/repos/hma13/TestApp/git/trees/03579b3ddb0d7fc3b74fc7ee75ec49635bdea6ac"
 * },
 * "url": "https://api.github.com/repos/hma13/TestApp/git/commits/2c3207c277129656c299ae0562562b51ac367df8",
 * "comment_count": 0,
 * "verification": {
 * "verified": false,
 * "reason": "unsigned",
 * "signature": null,
 * "payload": null
 * }
 * },
 */
public class CommitDetail {
    private PersonalInfo author;
    private PersonalInfo committer;
    private String message;

    public PersonalInfo getAuthor() {
        return author;
    }

    public PersonalInfo getCommitter() {
        return committer;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthorName() {
        return author != null ? author.getName() : null;
    }

    @Override
    public String toString() {
        return "CommitDetail{" +
                "author=" + author +
                ", committer=" + committer +
                ", message='" + message + '\'' +
                '}';
    }

    public static class PersonalInfo {
        private String name;
        private String email;
        private Long date;

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public Long getDate() {
            return date;
        }

        @Override
        public String toString() {
            return "PersonalInfo{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", date=" + date +
                    '}';
        }
    }
}
