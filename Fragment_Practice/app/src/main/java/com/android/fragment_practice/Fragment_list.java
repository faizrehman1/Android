package com.android.fragment_practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kamran ALi on 3/6/2016.
 */
public class Fragment_list extends Fragment {
    private Fragment_int_view fragmentIntView = new Fragment_int_view() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        Button incButton = (Button) view.findViewById(R.id.incrmentButton);
        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tc = (TextView) this.getView().
                fragmentIntView.getTargetFragment().getId().

                int a = fragmentIntView.i;
                a++ ;
            }
        });
        return view;

    }



}
