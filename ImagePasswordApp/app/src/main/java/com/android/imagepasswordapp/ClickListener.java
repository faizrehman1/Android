package com.android.imagepasswordapp;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/18/2016.
 */
public class ClickListener implements View.OnTouchListener {
    private List<Point> points = new ArrayList<Point>();
    public static final int CLICK_POINTS = 4 ;
    private PointsCollector pointsCollector;

    public void setPointsCollector(PointsCollector pointsCollector) {
        this.pointsCollector = pointsCollector;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Collected point %d , %d", x ,y );

        Log.d("TAG", message);
        points.add(new Point(x,y));

        if (points.size() == CLICK_POINTS ){
            pointsCollector.pointsCollectorListener(points);
        }
        return false;
    }
    public void clear(){
        points.clear();

    }
}
