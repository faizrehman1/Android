package com.android.passwordapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PointCollector {
    private ImageView imageView;
    private PointCollectorListener listener = new PointCollectorListener();
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


    @Override
    public void listener(List<Point> points) {
        Log.d("Tag", "Collected POints Are :" + points.size());

        pointsCollected(points);

        Log.d("TAG", "SuccessFully Stored POINTS");

        List<Point> point = dataBase.getPoint();
        for (Point point1 : point) {

            Log.d("TAG", String.format("STORED POINTS ARE = %d , %d", point1.x, point1.y));
        }

    }


    private void pointsCollected(final List<Point> points) {
        ////////////////////AlertDialog for Storing Points ////////////////
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stroing Points");
        final AlertDialog dialog = builder.create();
        dialog.show();

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                dataBase.storedPoints(points);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                dialog.dismiss();
            }
        };
        task.execute();

    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Create your PassPoint Sequence !");

        builder.setMessage("Touch Four POints to set Your pass point sequence .");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
