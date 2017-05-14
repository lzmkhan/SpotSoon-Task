package com.crystrom.spotsoontask;

/**
 * Created by Marcus Khan on 5/10/2017.
 */

public class Video {
    private String videoTitle;
    private String videoPostTime;
    private String videoDescription;
    private int videoImageIDs;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPostTime() {
        return videoPostTime;
    }

    public void setVideoPostTime(String videoPostTime) {
        this.videoPostTime = videoPostTime;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public int getVideoImageIDs() {
        return videoImageIDs;
    }

    public void setVideoImageIDs(int videoImageIDs) {
        this.videoImageIDs = videoImageIDs;
    }

    public Video(String videoTitle, String videoPostTime, String videoDescription, int videoImageIDs) {
        this.videoTitle = videoTitle;
        this.videoPostTime = videoPostTime;
        this.videoDescription = videoDescription;
        this.videoImageIDs = videoImageIDs;
    }
}
