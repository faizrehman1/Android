package com.android.editorapp;

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

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView helloWorldHd;
    private EditText email;
    private TextView emailHeading;
    private TextView pasHeading;
    private EditText pasword;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailHeading = (TextView) findViewById(R.id.emailHeading);
        email = (EditText) findViewById(R.id.email);
        pasHeading = (TextView) findViewById(R.id.pasHeading);
        pasword = (EditText) findViewById(R.id.pasword);
        loginBtn = (Button) findViewById(R.id.loginbtn);
        helloWorldHd = (TextView) findViewById(R.id.text2);

        helloWorldHd.setTextSize(25f);
        emailHeading.setTextSize(25f);
        pasHeading.setTextSize(25f);
        onclickFun();

    }

    private void onclickFun() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            String getEmail = email.getText().toString();
            String getPas = pasword.getText().toString();

            @Override
            public void onClick(View v) {
                Log.d("Tag", "OnClick Call");


                if ((getEmail.equalsIgnoreCase("abc")) && (getPas.equalsIgnoreCase("abc"))) {


                    Intent a = new Intent(MainActivity.this, Activity2.class);
                    startActivity(a);
                } else {
                    Log.d("Tag", "Else run");
                    helloWorldHd.setText("Wrong detail,Try Again!");
                }
            }
        });
    }


}
