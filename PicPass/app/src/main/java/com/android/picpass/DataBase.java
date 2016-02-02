package com.android.picpass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 1/30/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String TABLE_POINT = "POINTS";
    private static final String COL_ID = "ID";
    private static final String COL_X = "x";
    private static final String COL_Y = "y";

    public DataBase(Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("Create Table %s ( %s INTEGER primary key, %s INTEGER not null,%s INTEGER not null)", TABLE_POINT, COL_ID, COL_X, COL_Y);

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storePoints(List<Point> points) {

        SQLiteDatabase writableDatabase = getWritableDatabase();

        writableDatabase.delete(TABLE_POINT, null, null);

        int i = 0;
        for (Point point : points) {
            ContentValues values = new ContentValues();

            values.put(COL_ID, i);
            values.put(COL_X, point.x);
            values.put(COL_Y, point.y);
            writableDatabase.insert(TABLE_POINT, null, values);

            i++;
        }

        writableDatabase.close();


    }

    public List<Point> getPoint() {
        List<Point> points = new ArrayList<Point>();
        SQLiteDatabase readableDatabase = getReadableDatabase();

        String message = String.format("SELECT %s , %s FROM %s ORDER BY %s", COL_X, COL_Y, TABLE_POINT, COL_ID);


        Cursor cursor = readableDatabase.rawQuery(message, null);
        while (cursor.moveToNext()) {
            int x = cursor.getInt(0);
            int y = cursor.getInt(1);

            points.add(new Point(x, y));
        }

        readableDatabase.close();
        return points;
    }
}
