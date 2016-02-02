package com.android.image_password;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 1/28/2016.
 */
public class PointCollectorListener implements View.OnTouchListener{
    private List<Point> points = new ArrayList<Point>();
    private PointCollector listener ;

    public void setListener(PointCollector listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        points.add(new Point(x, y));

        String message = String.format("Collected Points Are ( %d , %d ) ",x,y);

        Log.d("Tag", message);
        if (points.size() == 4 ){
            listener.pointsListener(points);
            points.clear();
        }


        return false;
    }
}
