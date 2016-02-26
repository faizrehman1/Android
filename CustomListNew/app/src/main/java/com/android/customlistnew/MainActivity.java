package com.android.customlistnew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MessageAdapter adapter;
    private ListView listView;
    private Button button;
    private List<Message> messages = new ArrayList<Message>();
    private DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEmailList();
        addItemAlert();


    }

    private void addItemAlert() {
        button = (Button) findViewById(R.id.add_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialoge_layout, null);

                final EditText nameView = (EditText) view.findViewById(R.id.add_title_field);
                final EditText msgView = (EditText) view.findViewById(R.id.add_sender_field);
                final CheckBox condition = (CheckBox) view.findViewById(R.id.check_box);

                builder.setView(view);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = nameView.getText().toString();
                        String msg = msgView.getText().toString();
                        boolean cond = condition.isChecked();
                        messages.add(new Message(title, msg, 0, cond));
                        adapter.notifyDataSetChanged();
                        showPrompt();
                        //Toast.makeText(MainActivity.this, "Saving NewItem",Toast.LENGTH_SHORT).show();

                        List<Message> messages = db.reterivingMessages();
                        for (Message message : messages) {
                            Log.d("TAG", String.format("Saved Name is: %s , and Message is: %s", message.getTitle(), message.getSender()));
                        }
                    }
                });
                builder.create().show();
            }

        });
    }

    private void showPrompt() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Saving New Item");
        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                db.storeFields(messages);
                Log.d("TAG", "Messages Stored in DataBase " + messages.size());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

            dialog.dismiss();
            }
        };
        task.execute();
    }

    private void setupEmailList() {

//        messages.add(new Message("Kamran", "My name is Kamran", 0, false));
//        messages.add(new Message("Qasim", "My name is qasim", 1, true));
//        messages.add(new Message("Moosa", "My name is moosa", 2, true));
//        messages.add(new Message("Imran", "My name is imran", 3, true));

        listView = (ListView) findViewById(R.id.emailLst);
        /**
         * jaha data set kerwa rahy ho waha per database se data fetch kero na....
        */
        messages=db.reterivingMessages();
        
        adapter = new MessageAdapter(messages, this);

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Want to delete Item ?? Click delete");
                builder.setNegativeButton("Cancel", null);

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MessageAdapter adapter1 = new MessageAdapter(messages, MainActivity.this);



                        db.deleteItem(messages.get(position).getId());
                        messages.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.create().show();
                return true;
            }
        });



    }

}
