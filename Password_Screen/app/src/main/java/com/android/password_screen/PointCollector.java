package com.android.password_screen;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 1/23/2016.
 */
public class PointCollector implements View.OnTouchListener {
    private PointCollectorListener listener ;
    private List<Point> points = new ArrayList<Point>();


    public void setListener(PointCollectorListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Coordinate : (%d , %d )",x,y);

        Log.d("TAG", message);

        points.add(new Point(x,y));

        if (points.size() == 4){
                listener.pointsCollected(points);

        }

        return false;
    }
}
