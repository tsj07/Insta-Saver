package com.example.myapplication;

public class Second {

    private First user;
    private First video_versions;

    public Second(First user, First video_versions) {
        this.user = user;
        this.video_versions = video_versions;
    }

    public First getUser() {
        return user;
    }

    public void setUser(First user) {
        this.user = user;
    }

    public First getVideo_versions() {
        return video_versions;
    }

    public void setVideo_versions(First video_versions) {
        this.video_versions = video_versions;
    }
}