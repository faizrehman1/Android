package com.android.fragment_practice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    private Button mButton;
    private Communicator mCommunicator;
    int counter = 0;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            counter = 0;
        } else {
            counter = savedInstanceState.getInt("counter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        Log.d("Kamran", "OnCreate Frag");

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mButton = (Button) getActivity().findViewById(R.id.IncButton);
        mCommunicator = (Communicator) getActivity();
        mButton.setOnClickListener(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("counter", counter);
    }

    @Override
    public void onClick(View v) {
        counter++;
        mCommunicator.Responf("Button was Clicked " + counter + " times !");
    }
}
