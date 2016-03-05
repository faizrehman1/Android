package com.android.fragments_intro;

/**
 * Created by Kamran ALi on 3/5/2016.
 */
public class Course {
    private int imageid;
    private String title;
    private String description;

    public Course(int imageid, String title, String description) {
        this.imageid = imageid;
        this.title = title;
        this.description = description;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
