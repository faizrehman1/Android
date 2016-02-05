package com.android.picpasswordapp;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PointCollector {
    private TextView textView;
    private static final String PASSWORD_SET = "PASSWORD_SET";
    private ImageView imageView;
    private PointCollectorListener pointCollectorListener = new PointCollectorListener();
    private DataBase dataBase = new DataBase(this);
    private TextViewActivity textViewActivity = new TextViewActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.touch_Screen);
        textView = (TextView) findViewById(R.id.edit_text);
        imageView.setOnTouchListener(pointCollectorListener);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean passPointSet = preferences.getBoolean(PASSWORD_SET, false);

        if (!passPointSet) {
            showSetPointPromot();
        }
        pointCollectorListener.setListener(this);
    }

    private void showSetPointPromot() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Select any Points");
        builder.setMessage("Please Set Four Points as a Password of your app !");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
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
                Log.d("TAG", "POINTStored");
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

    private void verifyPassPoint(final List<Point> points) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void,Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {

                return true;
            }

            @Override
            protected void onPostExecute(Boolean pass) {
                if (pass == true){
                    Intent i = new Intent(MainActivity.this, TextViewActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, "Access Denied",Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
                pointCollectorListener.clear();
            }
        };
            task.execute();
    }

    @Override
    public void pointListener(final List<Point> points) {
//        Log.d("TAG", "Collected POints Size = " + points.size());
        {
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            Boolean passPointSet = preferences.getBoolean(PASSWORD_SET, false);

            if (!passPointSet) {
                Log.d("TAG", "StoringPoints ....");
                savePassPoint(points);
            } else {
                Log.d("TAG", "VerifyingPassPoints ....");
                verifyPassPoint(points);
            }

        }

//        Log.d("TAG", "POints Stored :" + points.size());
//        List<Point> points1 = dataBase.getPoints();
//        for (Point point : points) {
//            Log.d("TAG", String.format("Stored Coordinate are: %d , %d", point.x, point.y));
//        }
    }
}
