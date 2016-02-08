package com.android.image_passwordapp;

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

public class Image_Activity extends AppCompatActivity implements PointCollector {
    private static final String SET_PASSWORD = "SET_PASSWORD";
    private static final int POINT_CLOSSNESS = 40;

    private ImageView imageView;
    private PointListener pointListener = new PointListener();
    private DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_);
        imageView = (ImageView) findViewById(R.id.touch_Screen);
        imageView.setOnTouchListener(pointListener);
        pointListener.setPointCollector(this);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean setPassPoint = preferences.getBoolean(SET_PASSWORD, false);

        if (!setPassPoint) {
            showPrompt();
        }
    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Select your newPassword.");
        builder.setMessage("Select any Four Points to set your Password.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

    private void savePoints(final List<Point> points) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("StoringPoints ...");
        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                dataBase.storePoints(points);
                Log.d("TAG", "PointSaved : " + points.size());
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
                editor.putBoolean(SET_PASSWORD, true);
                editor.commit();

                pointListener.clear();
                dialog.dismiss();
            }
        };
        task.execute();
    }

    private void verifyPoints(final List<Point> touchedpoints) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("VerifyingPassPoints...");
        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Point> savedPoints = dataBase.getPoints();
                Log.d("TAG", "Loaded Points" + savedPoints.size());

                if (savedPoints.size() != pointListener.Num_Points || touchedpoints.size() != pointListener.Num_Points) {
                    return false;
                }
                for (int i = 0; i < pointListener.Num_Points; i++) {
                    Point savedPoint = savedPoints.get(i);
                    Point touchedPoint = touchedpoints.get(i);
                    //Calculating Difference b/w saved and touch points;
                    int xdiff = savedPoint.x - touchedPoint.x;
                    int ydiff = savedPoint.y - touchedPoint.y;
                    //squarring differnece b/w points;
                    int distanceSquared = xdiff * xdiff + ydiff * ydiff;

                    if (distanceSquared > POINT_CLOSSNESS*POINT_CLOSSNESS){
                        return false;
                    }


                }


                return true;
            }

            @Override
            protected void onPostExecute(Boolean pass) {
                if (pass == true) {
                    Intent i = new Intent(Image_Activity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Image_Activity.this, "AccessDenied", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        };
        task.execute();
    }

    @Override
    public void listener(final List<Point> points) {

        Log.d("TAG", "Collected Point SIze : " + points.size());
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean setPassPoint = preferences.getBoolean(SET_PASSWORD, false);

        if (setPassPoint == false) {
            Log.d("TAG", "SavingPassPoints ...");
            savePoints(points);
        } else {
            Log.d("TAG", "VerifyingPassPoints ...");
            verifyPoints(points);
        }


//        {   //GETTING POINTS FROM DATA BASE:
//            List<Point> points1 = dataBase.getPoints();
//            for (Point point : points) {
//                Log.d("TAG", String.format("StoredPoints: %d , %d !", point.x, point.y));
//            }
//        }
    }
}
