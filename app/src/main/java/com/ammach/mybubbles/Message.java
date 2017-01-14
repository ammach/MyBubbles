package com.ammach.mybubbles;

/**
 * Created by ammach on 8/23/2016.
 */
public class Message  {

    String text;
    String opt;
    String imgMessage;
    String videoUrl;


    public Message(String opt) {
        this.opt = opt;
    }

    public Message(String text, String opt) {
        this.text = text;
        this.opt = opt;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getImgMessage() {
        return imgMessage;
    }

    public void setImgMessage(String imgMessage) {
        this.imgMessage = imgMessage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }




}
