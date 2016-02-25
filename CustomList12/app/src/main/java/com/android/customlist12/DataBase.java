package com.android.customlist12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/24/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String TABLENAME = "todotable";
    private static final String COL_ID = "ID";
    private static final String COL_TITLE = "Title";
    private static final String COL_MSG = "Sender";
    private static final String COLSTAR = "STAR";

    public DataBase(Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE %s (  %s INTEGER primary key, %s TEXT not null , %s TEXT, %s TEXT )", TABLENAME, COL_ID, COL_TITLE, COL_MSG, COLSTAR);

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveData(List<Message> messages) {
        SQLiteDatabase db = getWritableDatabase();
        int i = 0;
        if (messages.size() > 1) {
            db.delete(TABLENAME, null, null);
            i = 0;
        } else {
            SQLiteDatabase dbcount = getReadableDatabase();
            String getQuery = String.format("select %s from %s", COL_ID, TABLENAME);
            Cursor cursor = dbcount.rawQuery(getQuery, null);
            while (cursor.moveToNext()) {
                i = cursor.getInt(0) + 1;
            }



        }
        for (Message message : messages) {
            ContentValues values = new ContentValues();
            values.put(COL_ID, i);
            values.put(COL_TITLE, message.getTitle());


            values.put(COL_MSG, message.getSender());

            Log.d("TAG", "While saving Boolean in Database Class isRead =" + message.isRead());

            values.put(COLSTAR, message.isRead());


            db.insert(TABLENAME, null, values);
            i++;
        }
        db.close();
    }
    public List<Message> retrievingDataBase() {
        List<Message> messages = new ArrayList<Message>();
        SQLiteDatabase db = getReadableDatabase();
        String getQuery = String.format("select %s,%s,%s from %s order by %s", COL_TITLE, COL_MSG, COLSTAR, TABLENAME, COL_ID);

        Cursor cursor = db.rawQuery(getQuery, null);

        while (cursor.moveToNext()) {

            String title = cursor.getString(0);

            String mg = cursor.getString(1);
            boolean star = false;

            if (cursor.getString(2).equals("1")) {
                star = true;
            }
            Log.d("TAG", "Star Boolean in Database Class is=" + star + " but String is " + cursor.getString(2));

            messages.add(new Message(title, mg, 0, star));
        }


        return messages;
    }
}
