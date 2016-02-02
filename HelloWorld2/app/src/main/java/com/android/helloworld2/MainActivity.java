package com.android.helloworld2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText editText;
    private Firebase firebase;
    private Button actButton;
    private Button buttonToPc;
    public static final String TextFile = "noteSquirel.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        button = (Button) findViewById(R.id.save);
        actButton = (Button) findViewById(R.id.activityRef);
        editText = (EditText) findViewById(R.id.editoor);
        buttonToPc = (Button) findViewById(R.id.buttonToPC);
        firebase = new Firebase("https://luminous-heat-6347.firebaseio.com/");
        saveBtnListnere();
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editText.setHint(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        openActivity();
        buttonToPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Tag", "Button to save in PC CLicked : ");
                try {
                    Toast.makeText(MainActivity.this, "Unable to save File!", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.d("Tag", "File Not found Exception Run");
                }
            }
        });

    }


    private void openActivity() {
        actButton.setOnClickListener(this);
    }

    private void saveBtnListnere() {
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.save):
                firebase.setValue(editText.getText().toString());
                break;
            case (R.id.activityRef):
                Intent a = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(a);
                break;
        }

    }
}
