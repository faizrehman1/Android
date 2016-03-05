package com.android.fragments_intro;

import java.util.ArrayList;

/**
 * Created by Kamran ALi on 3/5/2016.
 */
public class CourseList extends ArrayList<Course> {

    public CourseList() {
        add(new Course(R.drawable.sym_def_app_icon, "Java", "Mastering Java"));
        add(new Course(R.drawable.ic_corp_icon, "Android", "Mastering Android"));
    }
}
