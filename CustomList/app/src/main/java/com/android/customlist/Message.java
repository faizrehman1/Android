package com.android.customlist;

/**
 * Created by Kamran ALi on 2/19/2016.
 */
public class Message {
    private String sender;
    private String title;
    private int id;
    private Boolean read = false;


    public Message(int id, String title, String sender) {
        this.id = id;
        this.title = title;
        this.sender = sender;
    }

    public Message(int id, String title, String sender,Boolean read) {
        this.id = id;
        this.title = title;
        this.sender = sender;
        this.read = read;
    }
    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
