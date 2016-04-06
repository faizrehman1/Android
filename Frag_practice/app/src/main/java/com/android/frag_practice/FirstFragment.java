package com.android.frag_practice;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private ListView emailView;
    private List<Message> messages;
    private DataBase db;


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new DataBase(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        messages = new ArrayList<Message>();
        messages.add(new Message("Kamran", "My name is Kamran", 0));
        messages.add(new Message("Moosa", "My name is Kamran", 1));
        messages.add(new Message("Imran", "My name is Kamran", 2));
        messages.add(new Message("Ali", "My name is Kamran", 3));
        messages.add(new Message("Bilal", "My name is Kamran", 4));
        messages.add(new Message("Baba", "My name is Kamran", 5));
        messages.add(new Message("Qasim", "My name is Kamran", 6));

        MessageAdapter adapter = new MessageAdapter(messages, this);

        Log.d("Tag", "Siz of List is: " + messages.size());

        db.savingAllList(messages);
        Log.d("TAG","DAtabaseCreated");
        emailView = (ListView) view.findViewById(R.id.email_list);

        emailView.setAdapter(adapter);

        return view;


    }

    //Message Class Started
    public class Message {
        private String name;
        private String msg;
        private int id;
        private boolean read;

        public Message(String name, String msg, int id) {
            this.name = name;
            this.msg = msg;
            this.id = id;
        }

        public Message(String name, String msg, int id, boolean read) {
            this.name = name;
            this.msg = msg;
            this.id = id;
            this.read = read;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    } //Class Message ended;

    //AdapterClass Starts
    class MessageAdapter extends BaseAdapter implements ListAdapter {
        private List<Message> messages;
        private FirstFragment context;

        public MessageAdapter(List<Message> messages, FirstFragment context) {
            this.messages = messages;
            this.context = context;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return messages.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater(null).inflate(R.layout.email_list_layout, null);

            TextView name_View = (TextView) view.findViewById(R.id.nameView);
            TextView msg_View = (TextView) view.findViewById(R.id.msgView);
            ImageView iconView = (ImageView) view.findViewById(R.id.img_icon);

            final Message message = messages.get(position);
            String name = message.getName();
            String msg = message.getMsg();
            int iconId = message.isRead() ? R.drawable.btn_circle_pressed : R.drawable.btn_circle_disable;

            name_View.setText(name);
            msg_View.setText(msg);

            iconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (message.isRead()) {
                        message.setRead(false);
                    } else {
                        message.setRead(true);
                    }
                    notifyDataSetChanged();
                }
            });

            iconView.setImageResource(iconId);

            notifyDataSetChanged();

            return view;
        }
    } //Adapter Class Ended


}
