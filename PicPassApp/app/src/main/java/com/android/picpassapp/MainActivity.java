package com.android.picpassapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PointCollected {
    private ImageView imageView;
    private PointCollectedListener pointCollectedListener = new PointCollectedListener();
    private DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.touch_Screen);

        imageView.setOnTouchListener(pointCollectedListener);

        pointCollectedListener.setPointCollected(this);
        showPrompt();
    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Click any four points");
        builder.setMessage("Please Select Correct Four points to get Access of the App !");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

    @Override
    public void poinCollector(final List<Point> points) {
        {
            Log.d("Tag", "Collected Points  " + points.size());
        }
        {
            List<Point> points1 = dataBase.getPoints();
            for (Point point : points) {
                Log.d("TAG", String.format("Stored Points are = ( %d, %d)", point.x, point.y));
            }
        }

        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("StoringPoints ...");

            final AlertDialog dialog = builder.create();
            dialog.show();

            AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                    dataBase.storedPoints(points);
                    Log.d("Tag", "Points SuccessFully Stored !");
                    try {
                        Thread.sleep(1000);
                        Log.d("Tag", "Timer Run");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    dialog.dismiss();
                    Log.d("TAG", "Dismiss Run");

                }
            };
            task.execute();


        }
    }

}
