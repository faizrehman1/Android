package com.android.customlistnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/25/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String TODOSERVER = "DATA_BASE";
    private static final String TITLE = "name";
    private static final String MSG = "message";
    private static final String STAR = "checkBox";
    private static final String ID = "ID";

    public DataBase(Context context) {
        super(context, "TodoListDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s int primary key,%s TEXT not null,%s TEXT,%s TEXT)", TODOSERVER, ID, TITLE, MSG, STAR);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeFields(List<Message> messages) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

        writableDatabase.delete(TODOSERVER, null, null);

        int i = 0;

        for (Message message : messages) {
            ContentValues values = new ContentValues();

            values.put(ID, i);
            values.put(TITLE, message.getTitle());
            values.put(MSG, message.getSender());
            values.put(STAR, message.isCheckBox());
            i++;

            writableDatabase.insert(TODOSERVER, null, values);
        }
        writableDatabase.close();
    }

    public List<Message> reterivingMessages() {
        List<Message> messages = new ArrayList<Message>();

        SQLiteDatabase readableDatabase = getReadableDatabase();

        String sql = String.format("select %s,%s,%s from %s order by %s", TITLE, MSG, STAR, TODOSERVER, ID);

        Cursor cursor = readableDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            String sender = cursor.getString(1);
            boolean star = cursor.getWantsAllOnMoveCalls();

            Log.d("TAG", "Star Boolean in Database Class is=" + star + " but String is " + cursor.getString(1));

            messages.add(new Message(title, sender, 0, star));

        }
        readableDatabase.close();
        return messages;
    }

    ;
}
