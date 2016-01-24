package com.example.myhp.bunkerz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by my hp on 1/23/2016.
 */
public class Datahandle {
    public static final String KEY_NAME="subject_name";
    public static final String KEY_ATTENDANCE="subject_attendance";
    private static final String DATABASE_NAME="subject";
    private static final String DATABASE_TABLE="attend_table";
    private static final int DATABASE_VERSION=1;

    private Dbhelper ourhelper;
    private final Context ourcontext;
    private SQLiteDatabase ourdatabase;

    public void createentry(String s) {

        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,s);
        cv.put(KEY_ATTENDANCE,"0");
        ourdatabase.insert(DATABASE_TABLE,null,cv);
    }

    public String getData() {
        String[] columns=new String[] {KEY_NAME,KEY_ATTENDANCE};

        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";

        int iname=c.getColumnIndex(KEY_NAME);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+c.getString(iname)+"\n";

        }


        return result;
    }

    public int getattendance() {
        String[] columns=new String[] {KEY_NAME,KEY_ATTENDANCE};
        int result=0;
        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int iattend=c.getColumnIndex(KEY_ATTENDANCE);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+Integer.parseInt(c.getString(iattend));
        }

        return result;
    }

    private static class Dbhelper extends SQLiteOpenHelper {
        public Dbhelper(Context context) {
            super(context,DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+KEY_NAME+" TEXT NOT NULL, "+KEY_ATTENDANCE+" TEXT NOT NULL);");




        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
            onCreate(db);
        }
    }

    public Datahandle(Context c){
        ourcontext=c;
    }

    public Datahandle open()throws SQLException {
        ourhelper=new Dbhelper(ourcontext);
        ourdatabase=ourhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourhelper.close();
    }
}

