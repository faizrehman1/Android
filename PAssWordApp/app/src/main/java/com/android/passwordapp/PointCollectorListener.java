package com.android.passwordapp;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/1/2016.
 */
public class PointCollectorListener implements View.OnTouchListener {
    private PointCollector pointCollector;
    private List<Point> points = new ArrayList<Point>();

    public void setPointCollector(PointCollector pointCollector) {
        this.pointCollector = pointCollector;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Coordinates Are : %d , %d ", x, y);
        Log.d("Tag", message);

        points.add(new Point(x, y));

        if (points.size() == 4) {
            pointCollector.listener(points);
            points.clear();
        }

        return false;
    }
}
