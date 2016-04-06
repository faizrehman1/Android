package com.android.frag_practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 3/15/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String LISTDATABASE = "DataBase";
    private static final String COL_ID = "ID";
    private static final String COL_TITLE = "Name";
    private static final String COL_MSG = "MESSAGE";
    private static final String COL_CHECKBOX = "Condition";

    public DataBase(Context context) {
        super(context, "ListDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s INTEGER primary key AUTOINCREMENT,%s TEXT not null, %s TEXT , %s TEXT )", LISTDATABASE, COL_ID, COL_TITLE, COL_MSG, COL_CHECKBOX);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savingList(FirstFragment.Message messages) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_TITLE, messages.getName());
        values.put(COL_MSG, messages.getMsg());
        values.put(COL_CHECKBOX, messages.isRead());

        Log.d("TAG", "Message and title is" + messages.getName() + " : " + messages.getMsg());

        writableDatabase.insert(LISTDATABASE, null, values);

        writableDatabase.close();
    }


    public void savingAllList(List<FirstFragment.Message> messages) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();

        for (FirstFragment.Message m:messages) {
            ContentValues values = new ContentValues();

            values.put(COL_TITLE, m.getName());
            values.put(COL_MSG, m.getMsg());
            values.put(COL_CHECKBOX, m.isRead());

            Log.d("TAG", "Message and title is" + m.getName() + " : " + m.getMsg());

            writableDatabase.insert(LISTDATABASE, null, values);
        }
        writableDatabase.close();
    }

    public List<FirstFragment.Message> reterivingList() {
        List<FirstFragment.Message> messages = new ArrayList<FirstFragment.Message>();

        SQLiteDatabase readableDatabase = getReadableDatabase();

        String sql = String.format("select %s,%s,%s,%s from %s order by %s", COL_ID, COL_TITLE, COL_MSG, COL_CHECKBOX, LISTDATABASE, COL_ID);
        Cursor cursor = readableDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String msg = cursor.getString(2);
            String checkBoxx = cursor.getString(3);

            Log.d("ID is ", "MSG:" + id);

       //    messages.add(new FirstFragment.Message(title,msg,id));

        }


        readableDatabase.close();
        return messages;
    }
}
