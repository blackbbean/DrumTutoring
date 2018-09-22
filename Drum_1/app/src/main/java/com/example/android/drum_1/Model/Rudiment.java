package com.example.android.drum_1.Model;

/**
 * Created by Minjeong Kim on 2018-09-21.
 */

public class Rudiment {
    public String rudiment;
    public String url;

    public Rudiment(String rudiment, String url) {
        this.rudiment = rudiment;
        this.url = url;
    }
    public String getRudiment(){
        return rudiment;
    }
    public String getUrl(){
        return url;
    }
}
