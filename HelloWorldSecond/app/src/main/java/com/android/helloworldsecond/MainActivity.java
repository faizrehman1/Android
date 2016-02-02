package com.android.helloworldsecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Tag", "ONCreate Call");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textViewToChange);
//        textView.setText("Hello World");
        textView.setTextSize(45f);
        button = (Button) findViewById(R.id.button);
//        button.setTextSize(45f);
        OnclickFunction();
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Hello World!");
            }
        });

    }

    protected void OnclickFunction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("OnCLick Text Changed !");
                textView.setTextSize(20f);
//                button.setText("OnClick Text Changed!");
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d("Tag", "OnResume Call");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("Tag", "OnResume Call");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("Tag", "OnResume Call");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Tag", "OnResume Call");
        super.onDestroy();
    }
}

