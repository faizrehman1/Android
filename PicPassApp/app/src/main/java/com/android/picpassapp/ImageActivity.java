package com.android.picpassapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class ImageActivity extends AppCompatActivity implements PointCollector {
    private static final String PASSWORD_SET = "PASSWORD_SET";
    private static final int POINT_CLOSSNESS = 40;

    private ImageView imageView;
    private PointCollectorListener pointCollectorListener = new PointCollectorListener();
    private DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = (ImageView) findViewById(R.id.touch_Screen);
        imageView.setOnTouchListener(pointCollectorListener);

        pointCollectorListener.setListener(this);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean setPassPoint = preferences.getBoolean(PASSWORD_SET, false);

        if (!setPassPoint) {
            showPrompt();

        }
    }

    private void savePassPoint(final List<Point> points) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("StoringPoints ...");
        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                dataBase.storePoints(points);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(PASSWORD_SET, true);
                editor.commit();

                dialog.dismiss();
                pointCollectorListener.clear();

            }

        };
        task.execute();

    }

    private void verifyPassPoint(final List<Point> touchedPoints) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Checking Passpoints !");

        final AlertDialog dialog = builder.create();

        dialog.show();
        pointCollectorListener.clear();

        AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                List<Point> savedPoints = dataBase.getPoints();
                Log.d("TAG", "Loaded Points" + savedPoints.size());

                if (savedPoints.size() != pointCollectorListener.NUM_POINTS || touchedPoints.size() != pointCollectorListener.NUM_POINTS) {
                    return false;
                }
                for (int i = 0; i < pointCollectorListener.NUM_POINTS; i++) {
                    Point savedPoint = savedPoints.get(i);
                    Point touchPoint = touchedPoints.get(i);

                    int xDiff = savedPoint.x - touchPoint.x;
                    int yDiff = savedPoint.y - touchPoint.y;

                    int distSquared = xDiff * xDiff + yDiff * yDiff;
                    Log.d("TAG","distance squared " +distSquared);

                    if (distSquared > POINT_CLOSSNESS * POINT_CLOSSNESS) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean pass) {
                if (pass == true) {
                    Intent i = new Intent(ImageActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(ImageActivity.this, "AccessDeined", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        };
        task.execute();

    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Select your Password.");
        builder.setMessage("Please enter any four points to set your password!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

    @Override
    public void pointListener(final List<Point> points) {
        Log.d("TAG", "Collected Point size = " + points.size());
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean setPassPoint = preferences.getBoolean(PASSWORD_SET, false);

        if (!setPassPoint) {
            Log.d("TAG", "SavingPasspoints ....");
            savePassPoint(points);
        } else {
            Log.d("TAG", "VerifyingPassPoints ....");
            verifyPassPoint(points);
        }


//        Log.d("TAG", "POints Stored :" + points.size());
//        List<Point> points1 = dataBase.getPoints();
//        for (Point point : points) {
//            Log.d("TAG", String.format("Stored Points are: %d , %d ", point.x, point.y));
//        }
    }
}
