package com.android.frag_practice;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private Button mAddButton;
    private Button mFinsihButton;
    private ListView listView;
    private DataBase db;
    private List<Message> mMessages;
    private MessageAdapter adapter;
    private LayoutInflater getLayoutInflater;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_first, container, false);

        listView = (ListView) view.findViewById(R.id.email_list);
        mAddButton = (Button) view.findViewById(R.id.add_button);


        setupEmailList();
        addButtonPrompt();
        return view;
    }

    private void addButtonPrompt() {
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = getLayoutInflater.inflate(R.layout.dialogue_layout, null);
                final TextView mNameView = (TextView) view.findViewById(R.id.naming_Field);
                final TextView mMsgView = (TextView) view.findViewById(R.id.msg_Field);
                final CheckBox mCheckView = (CheckBox) view.findViewById(R.id.Check_box_Field);
                builder.setView(view);

                builder.setPositiveButton("AddItem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nameView = mNameView.getText().toString();
                        String msgView = mMsgView.getText().toString();
                        boolean mCheckViewChecked = mCheckView.isChecked();
                        Message newEntry = new Message(nameView, msgView, 0, mCheckViewChecked);
                        mMessages.add(newEntry);

                        db.savingList(mMessages);

                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", null);

                builder.create().show();
            }
        });
    }


    private void setupEmailList() {
        mMessages = new ArrayList<Message>();

        mMessages.add(new Message("Kamran", "My name is Kamran", 0, true));
        mMessages.add(new Message("Qasim", "My name is Qasim", 1, false));
        mMessages.add(new Message("BIlal", "My name is Bilal", 2, true));
//        mMessages.add(new Message("Moosa", "My name is Moosa", 3, true));
//        mMessages.add(new Message("Imran", "My name is Imran", 4, true));
//        mMessages.add(new Message("Baba", "My name is BABA", 5, true));

        Log.d("TAG", "Message Size is " + mMessages.size());

//        adapter = new MessageAdapter(mMessages, getActivity());

        db.savingList(mMessages);
        Log.d("DBSaved", "MEssagesList saved" + mMessages);
        mMessages = db.reterivingList();
        adapter = new MessageAdapter(mMessages, getActivity());

        listView.setAdapter(adapter);

    }


}
