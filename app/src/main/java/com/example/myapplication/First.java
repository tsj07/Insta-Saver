package com.example.myapplication;

public class First {

    String url;
    String profile_pic_url_hd;

    public First(String url, String profile_pic_url_hd) {
        this.url = url;
        this.profile_pic_url_hd = profile_pic_url_hd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfile_pic_url_hd() {
        return profile_pic_url_hd;
    }

    public void setProfile_pic_url_hd(String profile_pic_url_hd) {
        this.profile_pic_url_hd = profile_pic_url_hd;
    }
}

