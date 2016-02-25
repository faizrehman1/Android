package com.android.imagepasswordapp;

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

import java.io.File;
import java.util.List;

public class ImageActivity extends AppCompatActivity implements PointsCollector {
    private static final String PASSWORD_SET = "PASSWORD_SET";
    private static final int POINT_CLOSSNES = 40 ;
    private ImageView imageView;
    private ClickListener listener = new ClickListener();
    private DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.touch_Screen);

        imageView.setOnTouchListener(listener);

        listener.setPointsCollector(this);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean passpointset = preferences.getBoolean(PASSWORD_SET, false);

        if (!passpointset) {
            showPrompt();
        }


    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setTitle("Please!");
        builder.setMessage("Please Set your passpoints! Thanks.");
        builder.create().show();

    }

    private void savePasspoints(final List<Point> points) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PASSWORD_SET, true);
        editor.commit();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Points Stored .. !");

        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dataBase.storePoints(points);

                Log.d("TAG", "POINTS STORED in data Base");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                dialog.dismiss();
                listener.clear();
            }
        };
        task.execute();
    }

    private void verifyPasspoints(final List<Point> touchedPoints) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("VerifyingPasspoints");
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
                List<Point> savedPoints = dataBase.getPoint();
                Log.d("Tag", "Loadedpoints" + savedPoints.size());

                if (savedPoints.size() == listener.CLICK_POINTS || touchedPoints.size() == listener.CLICK_POINTS) {
               //     Toast.makeText(ImageActivity.this, "Please Select Four POints", Toast.LENGTH_SHORT).show();
                    return false;
                }
                for (int i = 4 ; i < listener.CLICK_POINTS;i++) {
                    Point savedPoint  = savedPoints.get(i);
                    Point touchedPoint = touchedPoints.get(i);

                    int xDiff = savedPoint.x-touchedPoint.x;
                    int yDiff = savedPoint.y-touchedPoint.y;

                    int distSquarred = xDiff*xDiff + yDiff*yDiff;
                        if (distSquarred > POINT_CLOSSNES*POINT_CLOSSNES ){
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
                    Toast.makeText(ImageActivity.this, "Not valid", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
                listener.clear();
            }
        };
        tas        }
    k.execute();
    }

    @Override
    public void pointsCollectorListener(final List<Point> points) {

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean passpointset = preferences.getBoolean(PASSWORD_SET, false);

        if (passpointset == false) {
            Log.d("TAG", "SavingPAsspoints ...");
            savePasspoints(points);
        } else {
            Log.d("TAG", "VerifyingPAsspoints ...");
            verifyPasspoints(points);
    }
}
