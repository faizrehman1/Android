package com.android.frag_practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 3/17/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String LISTDATABASE = "DataBase";
    private static final String ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_MSG = "MESSAGE";
    private static final String COL_CHECKVIEW = "CONDITION";

    public DataBase(Context context) {
        super(context, "FragmentListDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String mSql = String.format("create table %s (%s INTEGER primary key AUTOINCREMENT,%s TEXT not null,%s TEXT ,%s TEXT)", LISTDATABASE, ID, COL_NAME, COL_MSG, COL_CHECKVIEW);
        db.execSQL(mSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savingList(List<Message> messages) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

        for (Message m : messages) {
            ContentValues values = new ContentValues();

            values.put(COL_NAME, m.getName());
            values.put(COL_MSG, m.getMsg());
            values.put(COL_CHECKVIEW, m.isRead());

            Log.d("TAG", "Message and title is" + m.getName() + " : " + m.getMsg());

            writableDatabase.insert(LISTDATABASE, null, values);
        }
        writableDatabase.close();
    }

    public List<Message> reterivingList() {
        List<Message> messages = new ArrayList<Message>();

        SQLiteDatabase readableDatabase = getReadableDatabase();

        String mSql = String.format("select %s,%s,%s,%s from %s order by %s", ID, COL_NAME, COL_MSG, COL_CHECKVIEW, LISTDATABASE, ID);
        Cursor cursor = readableDatabase.rawQuery(mSql, null);

        while (cursor.moveToNext()) {
            int mId = cursor.getInt(0);
            String mName = cursor.getString(1);
            String mMsg = cursor.getString(2);
            String mCheckview = cursor.getString(3);
            Log.d("ID is ", "MSG:" + mId);
            messages.add(new Message(mName, mMsg, mId, Boolean.valueOf(mCheckview)));

        }

        readableDatabase.close();

        return messages;
    }
}
