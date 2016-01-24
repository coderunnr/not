package com.example.myhp.bunkerz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by my hp on 1/24/2016.
 */
public class Timetable {
    public static final String KEY_DAY="day";
    public static final String KEY_NAME="subject_name";
    public static final String START="subject_start";
    public static final String STOP="subject_stop";
    private static final String DATABASE_NAME="timetable";
    private static final String DATABASE_TABLE="subjecttime";
    private static final int DATABASE_VERSION=1;

    private Dbhelper ourhelper;
    private final Context ourcontext;
    private SQLiteDatabase ourdatabase;

    public void createentry(String s,String start,String stop,String day) {

        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,s);
        cv.put(START,start);
        cv.put(STOP,stop);
        cv.put(KEY_DAY,day);
        ourdatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String[] columns=new String[] {KEY_NAME,START,STOP,KEY_DAY};

        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";

        int iname=c.getColumnIndex(KEY_NAME);
        int istart=c.getColumnIndex(START);
        int istop=c.getColumnIndex(STOP);
        int iday=c.getColumnIndex(KEY_DAY);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+c.getString(iname)+" "+c.getString(istart)+" "+c.getString(istop)+" "+c.getString(iday)+"\n";

        }


        return result;
    }



    private static class Dbhelper extends SQLiteOpenHelper {
        public Dbhelper(Context context) {
            super(context,DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+KEY_NAME+" TEXT NOT NULL, "+START+" TEXT NOT NULL, "+STOP+" TEXT NOT NULL, "+KEY_DAY+" TEXT NOT NULL);");




        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
            onCreate(db);
        }
    }

    public Timetable(Context c){
        ourcontext=c;
    }

    public Timetable open()throws SQLException {
        ourhelper=new Dbhelper(ourcontext);
        ourdatabase=ourhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourhelper.close();
    }
}
