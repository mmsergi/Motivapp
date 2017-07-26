package com.sergi.motivapp.models;

/**
 * Created by gersoft on 26/07/2017.
 */

public class VideoToken {

    public String title, url;
    public int points;

    public VideoToken(String title, String url, int points){
        this.title = title;
        this.url = url;
        this.points = points;

    }
}
