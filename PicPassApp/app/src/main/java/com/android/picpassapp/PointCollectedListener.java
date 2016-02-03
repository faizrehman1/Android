package com.android.picpassapp;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/3/2016.
 */
public class PointCollectedListener implements View.OnTouchListener {
    private List<Point> points = new ArrayList<Point>();
    private PointCollected pointCollected;

    public void setPointCollected(PointCollected pointCollected) {
        this.pointCollected = pointCollected;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Coordinate ARe = %d , %d",x,y);

        points.add(new Point(x, y));

        Log.d("TAG", message);

        if (points.size() == 4){
            pointCollected.poinCollector(points);
            points.clear();
        }
        return false;
    }
}
