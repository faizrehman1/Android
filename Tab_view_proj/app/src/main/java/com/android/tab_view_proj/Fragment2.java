package com.android.tab_view_proj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kamran ALi on 3/9/2016.
 */
public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag2,container,false);
        TextView textView = (TextView) view.findViewById(R.id.textview2);

        textView.setText("This is Fragment 2");
        return view;
    }
}

