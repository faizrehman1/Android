package com.android.picpassapp;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/5/2016.
 */
public class PointCollectorListener implements View.OnTouchListener {
    private List<Point> points = new ArrayList<Point>();
    private PointCollector listener;
    public static final int NUM_POINTS = 4 ;

    public void setListener(PointCollector listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Coordiate are ( %d , %d )", x, y);
        Log.d("TAG", message);

        points.add(new Point(x,y));
        if (points.size() == NUM_POINTS ){
            listener.pointListener(points);
        }
        return false;
    }
    public void clear(){
        points.clear();
    }
}
