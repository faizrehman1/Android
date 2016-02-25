package com.android.custom_list_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MessageAdapter adapter;
    private List<Message> messages = new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEmailList();


        addButtonToList();


    }

    private void addButtonToList() {
        Button button = (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialoge_layout, null);
                final EditText titleView = (EditText) view.findViewById(R.id.name_field);
                final EditText senderView = (EditText) view.findViewById(R.id.message_field);
                final CheckBox condition = (CheckBox) view.findViewById(R.id.check_box);

                builder.setView(view);
                builder.setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = titleView.getText().toString();
                        String sender = senderView.getText().toString();
                        boolean checkBOX = condition.isChecked();

                        messages.add(new Message(sender, title, 0, checkBOX));

                        adapter.notifyDataSetChanged();

                    }

                });
                builder.create().show();
            }
        });
    }

    private void setupEmailList() {



        messages.add(new Message("My name is Kamran.", "Kamran", 0, true));
        messages.add(new Message("My name is Kamran.", "Imran", 2, false));
        messages.add(new Message("My name is Kamran.", "Bilal", 3, false));
        messages.add(new Message("My name is Kamran.", "Moosa", 4, false));
        messages.add(new Message("My name is Kamran.", "Qasim", 5, false));

        ListView listView = (ListView) findViewById(R.id.emailList);

        adapter = new MessageAdapter(messages, this);

        listView.setAdapter(adapter);

    }
}
