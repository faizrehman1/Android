package com.android.password_screen;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PointCollectorListener{
    private ImageView imageView;
    private PointCollector pointCollector = new PointCollector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.touch_Screen);
        imageView.setOnTouchListener(pointCollector);
        pointCollector.setListener(this);
    }

    @Override
    public void pointsCollected(List<Point> points) {
        Log.d("TAG" , "Collected Points "+points.size() );
    }
}
