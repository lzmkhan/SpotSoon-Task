package com.crystrom.spotsoontask;

/**
 * Created by Marcus Khan on 5/10/2017.
 */

public class Song {
    //Song's main title
    private String songTitle;
    //Song's sub title
    private String songSubTitle;
    //Song's Image Resouce IDs
    private int songImageIds;
    //Song's source if we are playing it from online or from local [Not implemented]
    private String songSrc;

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongSubTitle() {
        return songSubTitle;
    }

    public void setSongSubTitle(String songSubTitle) {
        this.songSubTitle = songSubTitle;
    }

    public int getSongImageIds() {
        return songImageIds;
    }

    public void setSongImageIds(int songImageIds) {
        this.songImageIds = songImageIds;
    }

    public String getSongSrc() {
        return songSrc;
    }

    public void setSongSrc(String songSrc) {
        this.songSrc = songSrc;
    }

    public Song(String songTitle, String songSubTitle, int songImageIds, String songSrc) {
        this.songTitle = songTitle;
        this.songSubTitle = songSubTitle;
        this.songImageIds = songImageIds;
        this.songSrc = songSrc;
    }
}
