package com.example.github.data;

import androidx.room.Embedded;

import java.util.Objects;

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
public class CommitInfo {
    @Embedded(prefix = "author")
    private PersonalInfo author;
    @Embedded(prefix = "committer")
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

    public void setAuthor(PersonalInfo author) {
        this.author = author;
    }

    public void setCommitter(PersonalInfo committer) {
        this.committer = committer;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitInfo that = (CommitInfo) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(committer, that.committer) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, committer, message);
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

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setDate(Long date) {
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonalInfo that = (PersonalInfo) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(email, that.email) &&
                    Objects.equals(date, that.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, email, date);
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
