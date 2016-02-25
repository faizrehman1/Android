package com.android.customlist12;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //    ImageView iconView;
//    Boolean flag;
    private List<Message> messages = new ArrayList<Message>();
    private MessageAdapter adapter;
    private DataBase db = new DataBase(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEmailList();

        addListItemButton();
    }


    private void setupEmailList() {

        messages.add(new Message("My name is Kamran.", "Kamran", 0, false));
        messages.add(new Message("My name is Qasim.", "Qasim", 1, true));
        messages.add(new Message("My name is Moosa.", "Moosa", 2, true));
        messages.add(new Message("My name is Bilal.", "Bilal", 3, true));
        messages.add(new Message("My name is Imran.", "Imran", 4, true));

        ListView listView = (ListView) findViewById(R.id.emailList);

        adapter = new MessageAdapter(messages, this);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void addListItemButton() {
        Button button = (Button) findViewById(R.id.addItem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Add Item");
                alert.setNegativeButton("Cancel", null);

                View view = getLayoutInflater().inflate(R.layout.dialoge_layout, null);

                final EditText nameView = (EditText) view.findViewById(R.id.name_field);
                final EditText messageView = (EditText) view.findViewById(R.id.message_field);
                final CheckBox checkBoxView = (CheckBox) view.findViewById(R.id.check_box);
                alert.setView(view);

                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = nameView.getText().toString();
                        String sender = messageView.getText().toString();
                        boolean condition = checkBoxView.isChecked();

                        if (!title.equals("")) {
                            messages.add(new Message(sender, title, 0, condition));
                            db.saveData(messages);
                            messages.clear();
                            Log.d("TAG", "Data Added to Database");

                            adapter.notifyDataSetChanged();
                        } else {

                            Toast.makeText(MainActivity.this, "Title Must Be Filled", Toast.LENGTH_LONG).show();
                        }
                        Log.d("TAG", title + " " + sender);
                    }
                });
                alert.create().show();
            }

        });

    }


}
