package com.android.picpasswordapp;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/4/2016.
 */
public class PointCollectorListener implements View.OnTouchListener {

    private List<Point> points = new ArrayList<Point>();
    private PointCollector listener ;

    public void setListener(PointCollector listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Coordinate are: %d , %d",x,y);

        Log.d("TAG", message);

        points.add(new Point(x,y));

        if (points.size() == 4){
                listener.pointListener(points);
        }
        return false;
    }
    public void clear(){
        points.clear();
    }
}
