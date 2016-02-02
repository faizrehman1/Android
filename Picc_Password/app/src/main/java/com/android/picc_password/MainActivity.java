package com.android.picc_password;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Point_Collector {
    private ImageView imageView;
    private PointCollectorListener pointCollectorListener = new PointCollectorListener();
    private Data_Base data_base = new Data_Base(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.touch_Screen);
        imageView.setOnTouchListener(pointCollectorListener);
        pointCollectorListener.setListener(this);
        showPrompt();

    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Please Select Correct Points!");
        builder.setMessage("Please Select four Correct points to get Access OF the APP !");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

    @Override
    public void Listener(List<Point> points) {
        Log.d("Tag", "Total Collected Coordinates = " + points.size());
        data_base.storedPoints(points);

        List<Point> point = data_base.getPoint();
        for (Point point1 : point){
            Log.d("TAG",String.format("Stored points are ( %d , %d )",point1.x,point1.y));
        }

    }

}
