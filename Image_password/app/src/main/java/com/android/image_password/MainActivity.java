package com.android.image_password;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PointCollector {
    private  DataBase db = new DataBase(this);
    private static final int CAMERA_REQUEST = 1888;

    private ImageView imageView;

    private PointCollectorListener pointCollectorListener = new PointCollectorListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.touch_screen);

        imageView.setOnTouchListener(pointCollectorListener);
        pointCollectorListener.setListener(this);

        showPrompt();

    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Spot four Points to Unlock!");
        builder.setMessage("Please Spot correct POINTS to gain Access of the App ! \n \t \t Thanks");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

    @Override
    public void pointsListener(List<Point> points) {
        Log.d("Tag", "Collected Points ==" + points.size());
        db.storePoints(points);
    }

}
