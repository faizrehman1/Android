package com.android.customlistnew;

/**
 * Created by Kamran ALi on 2/25/2016.
 */
public class Message {
    private String title ;
    private String sender ;
    private int id ;
    private boolean checkBox ;

    public Message(String title, String sender, int id) {
        this.title = title;
        this.sender = sender;
        this.id = id;
    }
    public Message(String title, String sender, int id,boolean checkBox) {
        this.title = title;
        this.sender = sender;
        this.id = id;
        this.checkBox = checkBox ;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
