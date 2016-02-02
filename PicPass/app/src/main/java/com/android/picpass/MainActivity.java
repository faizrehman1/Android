package com.android.picpass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PointCollector{
    private ImageView imageView;
    private PointCollecterListener listener = new PointCollecterListener();
    private DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.touch_Screen);
        imageView.setOnTouchListener(listener);
        listener.setPointCollector(this);


        showPrompt();
    }

    public void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Click Any of the Four POints !");
        builder.setMessage("Please Select Four Correct points to get Access of the APP !");


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void pointCollector(List<Point> points) {
        Log.d("TAG", "POINTs Collected == " + points.size());

        dataBase.storePoints(points);
        List<Point> point = dataBase.getPoint();

        for (Point point1 : point){
            Log.d("TAG", String.format("Stored points are ( %d , %d )",point1.x,point1.y));
        }


    }
}
