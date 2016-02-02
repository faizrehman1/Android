package com.android.picpass;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 1/30/2016.
 */
public class PointCollecterListener implements View.OnTouchListener {
    private List<Point> points = new ArrayList<Point>();
    private PointCollector pointCollector;

    public void setPointCollector(PointCollector pointCollector) {
        this.pointCollector = pointCollector;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Coordinate are : (%d , %d )",x,y);

        points.add(new Point(x, y));


        Log.d("TAG", message);
        if (points.size() == 4 ){
            pointCollector.pointCollector(points);
            points.clear();

        }

        return false;
    }
}
