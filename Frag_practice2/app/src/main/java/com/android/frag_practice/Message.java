package com.android.frag_practice;

/**
 * Created by Kamran ALi on 3/17/2016.
 */
public class Message {
    private String mName;
    private String mMsg;
    private int mId;
    private boolean mRead;

    public Message(String name, String msg, int id, boolean read) {
        mName = name;
        mMsg = msg;
        mId = id;
        mRead = read;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean read) {
        mRead = read;
    }
}
