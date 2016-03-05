package com.android.fragments_intro;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Kamran ALi on 3/5/2016.
 */
public class CourseListFragment extends ListFragment {
    private CourseList courses = new CourseList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(getActivity(), android.R.layout.simple_list_item_1, courses);

        setListAdapter(adapter);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Course course = courses.get(position);

        Toast.makeText(getActivity(), course.getDescription(), Toast.LENGTH_SHORT).show();
    }
}
