package com.android.customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpEmailList();
    }

    private void setUpEmailList() {
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(0,"Kamran","Where are you ?",true));
        messages.add(new Message(2,"Qasim","I'm busy filhal",false));
        messages.add(new Message(3,"Moosa","I'm android Developer.",false));
        messages.add(new Message(4,"Imrana","I'm PSF .",false));
        messages.add(new Message(5,"Bilala","I'm PTCL .",false));
        messages.add(new Message(6,"Uncla","I'm Coat me Chokidaar.",false));
        messages.add(new Message(7,"Ali","I'm Choro.",false));
        messages.add(new Message(8,"Faiz","I'm Fuustian.",false));
        messages.add(new Message(9,"Joji","I'm ghat Jugaari.",false));

        MessageAdapter adapter = new MessageAdapter(this , messages);
        listView = (ListView) findViewById(R.id.emailList);

        listView.setAdapter(adapter);
    }
}
