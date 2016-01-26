package com.example.myhp.bunkerz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.Calendar;

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

    public String[] gettv(int day) {
        String result1="";
        switch(day){
            case 1:
                String[] columns1=new String[] {KEY_NAME,START,STOP,KEY_DAY};

                Cursor c1=ourdatabase.query(DATABASE_TABLE,columns1,KEY_DAY+"=\"Sunday\"",null,null,null,null);
                int iname1=c1.getColumnIndex(KEY_NAME);
                for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext()){
                    result1=result1+""+c1.getString(iname1)+"\n";
                }

                break;

            case 2:
                String[] columns2=new String[] {KEY_NAME,START,STOP,KEY_DAY};

                Cursor c2=ourdatabase.query(DATABASE_TABLE,columns2,KEY_DAY+"=\"Monday\"",null,null,null,null);
                int iname2=c2.getColumnIndex(KEY_NAME);
                for(c2.moveToFirst();!c2.isAfterLast();c2.moveToNext()){
                    result1=result1+""+c2.getString(iname2)+"\n";
                }

                break;
            case 3:
                String[] columns3=new String[] {KEY_NAME,START,STOP,KEY_DAY};

                Cursor c3=ourdatabase.query(DATABASE_TABLE,columns3,KEY_DAY+"=\"Tuesday\"",null,null,null,null);
                int iname3=c3.getColumnIndex(KEY_NAME);
                for(c3.moveToFirst();!c3.isAfterLast();c3.moveToNext()){
                    result1=result1+""+c3.getString(iname3)+"\n";
                }

                break;
            case 4:
                String[] columns4=new String[] {KEY_NAME,START,STOP,KEY_DAY};

                Cursor c4=ourdatabase.query(DATABASE_TABLE,columns4,KEY_DAY+"=\"Wednesday\"",null,null,null,null);
                int iname4=c4.getColumnIndex(KEY_NAME);
                for(c4.moveToFirst();!c4.isAfterLast();c4.moveToNext()){
                    result1=result1+""+c4.getString(iname4)+"\n";
                }

                break;
            case 5:
                String[] columns5=new String[] {KEY_NAME,START,STOP,KEY_DAY};

                Cursor c5=ourdatabase.query(DATABASE_TABLE,columns5,KEY_DAY+"=\"Thursday\"",null,null,null,null);
                int iname5=c5.getColumnIndex(KEY_NAME);
                for(c5.moveToFirst();!c5.isAfterLast();c5.moveToNext()){
                    result1=result1+""+c5.getString(iname5)+"\n";
                }

                break;
            case 6:
                String[] columns6=new String[] {KEY_NAME,START,STOP,KEY_DAY};

                Cursor c6=ourdatabase.query(DATABASE_TABLE,columns6,KEY_DAY+"=\"Friday\"",null,null,null,null);
                int iname6=c6.getColumnIndex(KEY_NAME);
                for(c6.moveToFirst();!c6.isAfterLast();c6.moveToNext()){
                    result1=result1+""+c6.getString(iname6)+"\n";
                }

                break;
            case 7:
                String[] columns7=new String[] {KEY_NAME,START,STOP,KEY_DAY};

                Cursor c7=ourdatabase.query(DATABASE_TABLE,columns7,KEY_DAY+"=\"Saturday\"",null,null,null,null);
                int iname7=c7.getColumnIndex(KEY_NAME);
                for(c7.moveToFirst();!c7.isAfterLast();c7.moveToNext()){
                    result1=result1+""+c7.getString(iname7)+"\n";
                }

                break;

      }
        return result1.split("\n");
    }

    public void droptable() {
        ourdatabase.execSQL("DROP TABLE "+DATABASE_TABLE);
        ourdatabase.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+KEY_NAME+" TEXT NOT NULL, "+START+" TEXT NOT NULL, "+STOP+" TEXT NOT NULL, "+KEY_DAY+" TEXT NOT NULL);");
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
