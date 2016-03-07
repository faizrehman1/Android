package com.android.fragment_practice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kamran ALi on 3/6/2016.
 */
public class Fragment_int_view extends Fragment {
    private IntViewer intViewer = new IntViewer(1);
    int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intview, container, false);
        TextView intView = (TextView) view.findViewById(R.id.intView);

        intView.setText(i);

        return view;
    }
}
