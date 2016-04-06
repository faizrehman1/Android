package com.android.fragment_practice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecFragment extends Fragment {

    private TextView mTextView;
    private String data;

    public SecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sec, container, false);
//        if (savedInstanceState == null) {
//
//        } else {
//            data = savedInstanceState.getString("data");
//            mTextView = (TextView) getActivity().findViewById(R.id.text_view);
//            mTextView.setText(data);
//        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextView = (TextView) getActivity().findViewById(R.id.text_view);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", data);
    }

    public void changeText(String data) {
        this.data = data;
        mTextView.setText(data);

    }
}
