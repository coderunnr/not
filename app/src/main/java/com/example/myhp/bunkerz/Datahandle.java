package com.example.myhp.bunkerz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by my hp on 1/23/2016.
 */
public class Datahandle {
    public static final String KEY_NAME="subject_name";
    public static final String KEY_ATTENDANCE="subject_attendance";
    public static final String KEY_MASSBUNK="subject_massbunk";
    public static final String KEY_TOTAL="subject_total";
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
        cv.put(KEY_TOTAL,"0");
        cv.put(KEY_MASSBUNK,"0");
        ourdatabase.insert(DATABASE_TABLE,null,cv);
    }

    public String getData() {
        String[] columns=new String[] {KEY_NAME,KEY_ATTENDANCE,KEY_TOTAL,KEY_MASSBUNK};

        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";

        int iname=c.getColumnIndex(KEY_NAME);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+c.getString(iname)+"\n";

        }


        return result;
    }

    public int getattendance() {
        String[] columns=new String[] {KEY_NAME,KEY_ATTENDANCE,KEY_TOTAL,KEY_MASSBUNK};
        int result=0;
        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int iattend=c.getColumnIndex(KEY_ATTENDANCE);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+Integer.parseInt(c.getString(iattend));
        }

        return result;
    }

    public void updateattendance(int which,String s) {
        String l=getattendforupdate(s,which);
        String t=gettotalattendance(s);
        if(l!=null&&which!=2) {
            int j = Integer.parseInt(l);
            j = j + which;
            l = Integer.toString(j);
            int to=Integer.parseInt(t);
            to++;
            t=Integer.toString(to);

            ourdatabase.execSQL("UPDATE " + DATABASE_TABLE + " SET " + KEY_ATTENDANCE + "=" + "\""+l+"\"" + " WHERE " + KEY_NAME + "=" + "\""+s+"\"");
            ourdatabase.execSQL("UPDATE " + DATABASE_TABLE + " SET " + KEY_TOTAL + "=" + "\""+t+"\"" + " WHERE " + KEY_NAME + "=" + "\""+s+"\"");
        }else if(which==2){
            int i = Integer.parseInt(l);
            i++;
            l = Integer.toString(i);
            int at=Integer.parseInt(t);
            at++;
            t=Integer.toString(at);
            ourdatabase.execSQL("UPDATE " + DATABASE_TABLE + " SET " + KEY_MASSBUNK + "=" + "\""+l+"\"" + " WHERE " + KEY_NAME + "=" + "\""+s+"\"");
            ourdatabase.execSQL("UPDATE " + DATABASE_TABLE + " SET " + KEY_TOTAL + "=" + "\""+t+"\"" + " WHERE " + KEY_NAME + "=" + "\""+s+"\"");
        }

    }

    public String gettotalattendance(String s) {
        Cursor c=ourdatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_NAME + "=" + "\"" + s + "\"", null);
        // KEY_NAME+"="+"\""+s+"\""
        String result="";


        if(c!=null) {
            int iattend = c.getColumnIndex(KEY_TOTAL);
            c.moveToFirst();

            result = c.getString(iattend);


            return result;
        }
        return null;
    }

    private String getattendforupdate(String s,int which) {

        Cursor c=ourdatabase.rawQuery("SELECT * FROM "+DATABASE_TABLE+" WHERE "+KEY_NAME+"="+"\""+s+"\"",null);
       // KEY_NAME+"="+"\""+s+"\""
        String result="";


       if(c!=null&&c.getCount()>0&&which!=2) {
           int iattend=c.getColumnIndex(KEY_ATTENDANCE);
           c.moveToFirst();

           result = c.getString(iattend);


           return result;
       }
        else if(which==2){
           int imass=c.getColumnIndex(KEY_MASSBUNK);
           c.moveToFirst();

           result = c.getString(imass);
           return result;
       }
        return null;

    }

    public int gettotalattendance() {
        String[] columns=new String[] {KEY_NAME,KEY_ATTENDANCE,KEY_TOTAL,KEY_MASSBUNK};
        int result=0;
        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int itotal=c.getColumnIndex(KEY_TOTAL);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+Integer.parseInt(c.getString(itotal));
        }

        return result;
    }

    public String getpresent(String s) {
        Cursor c=ourdatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_NAME + "=" + "\"" + s + "\"", null);
        // KEY_NAME+"="+"\""+s+"\""
        String result="";


        if(c!=null&&c.getCount()>0) {
            int iattend = c.getColumnIndex(KEY_ATTENDANCE);
            c.moveToFirst();

            result = c.getString(iattend);


            return result;
        }
        return null;
    }

    public String getmassbunk(String s) {
        Cursor c=ourdatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_NAME + "=" + "\"" + s + "\"", null);
        // KEY_NAME+"="+"\""+s+"\""
        String result="";


        if(c!=null&&c.getCount()>0) {
            int iattend = c.getColumnIndex(KEY_MASSBUNK);
            c.moveToFirst();

            result = c.getString(iattend);


            return result;
        }
        return null;
    }

    public void deleteattendance(int which, String s) {
        String l=getattendforupdate(s, which);
        String t=gettotalattendance(s);
        if(l!=null) {
            int j = Integer.parseInt(l);
            j = j-1;
            l = Integer.toString(j);
            int to=Integer.parseInt(t);
            to--;
            t=Integer.toString(to);

            ourdatabase.execSQL("UPDATE " + DATABASE_TABLE + " SET " + KEY_ATTENDANCE + "=" + "\"" + l + "\"" + " WHERE " + KEY_NAME + "=" + "\"" + s + "\"");
            ourdatabase.execSQL("UPDATE " + DATABASE_TABLE + " SET " + KEY_TOTAL + "=" + "\""+t+"\"" + " WHERE " + KEY_NAME + "=" + "\""+s+"\"");
        }
    }

    public int gettotalmassbunk() {
        String[] columns=new String[] {KEY_NAME,KEY_ATTENDANCE,KEY_TOTAL,KEY_MASSBUNK};
        int result=0;
        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int itotal=c.getColumnIndex(KEY_MASSBUNK);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+Integer.parseInt(c.getString(itotal));
        }

        return result;

    }


    private static class Dbhelper extends SQLiteOpenHelper {
        public Dbhelper(Context context) {
            super(context,DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+KEY_NAME+" TEXT NOT NULL, "+KEY_ATTENDANCE+" TEXT NOT NULL, "+KEY_TOTAL+" TEXT NOT NULL, "+KEY_MASSBUNK+" TEXT NOT NULL);");




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

