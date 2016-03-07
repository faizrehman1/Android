package com.android.fragments_intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private FirstFragment firstFragment = new FirstFragment();
    public int id = firstFragment.id;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_second, container, false);
        Button IncButton = (Button) view.findViewById(R.id.incButton);
        Log.d("ID", "Id is : " + id);

        IncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = ++id;
                Log.d("ID", "Id is afterInc: " + id);
                setText(String.valueOf(id));
            }
        });


        return view;
    }

    public void setText(String text) {
        TextView textView = (TextView) getActivity().findViewById(R.id.txtview);
        textView.setText(text);
    }

}
